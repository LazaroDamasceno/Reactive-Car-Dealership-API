package com.api.v1.services.cars;

import com.api.v1.domain.cars.CarInventory;
import com.api.v1.domain.cars.CarInventoryRepository;
import com.api.v1.domain.cars.Cars;
import com.api.v1.domain.cars.CarsRepository;
import com.api.v1.dtos.cars.CarRegistrationRequestDto;
import com.api.v1.dtos.cars.CarResponseDto;
import com.api.v1.exceptions.cars.DuplicatedVinException;
import com.api.v1.utils.cars.CarResponseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CarRegistrationServiceImpl implements CarRegistrationService {

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private CarInventoryRepository carInventoryRepository;

    @Override
    public Mono<CarResponseDto> register(@Valid CarRegistrationRequestDto requestDto) {
        return carsRepository
                .findAll()
                .filter(e -> e.getVin().equals(requestDto.vin()))
                .hasElements()
                .flatMap(exists -> {
                    if (exists) return Mono.error(new DuplicatedVinException(requestDto.vin()));
                    return Mono.defer(() -> {
                       Cars car =  Cars.create(requestDto);
                       return carsRepository.save(car)
                               .flatMap(savedCar -> {
                                   CarInventory carInventory = CarInventory.create(savedCar, requestDto.quantity());
                                   return carInventoryRepository.save(carInventory);
                               })
                               .flatMap(savedCar -> Mono.just(CarResponseMapper.map(savedCar.getCar())));
                    });
                });
    }

}
