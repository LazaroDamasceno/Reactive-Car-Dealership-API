package com.api.v1.services.cars;

import com.api.v1.domain.cars.CarsRepository;
import com.api.v1.dtos.cars.CarResponseDto;
import com.api.v1.exceptions.cars.CarNotFoundException;
import com.api.v1.utils.cars.CarFinderUtil;
import com.api.v1.utils.cars.CarResponseMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class CarRetrievalServiceImpl implements CarRetrievalService {

    @Autowired
    private CarFinderUtil carFinderUtil;

    @Autowired
    private CarsRepository carsRepository;

    @Override
    public Mono<CarResponseDto> findByVin(@NotBlank @Size(min=13, max=13) String vin) {
        return carFinderUtil
                .find(vin)
                .switchIfEmpty(Mono.error(new CarNotFoundException(vin)))
                .flatMap(car -> Mono.just(CarResponseMapper.map(car)));
    }

    @Override
    public Flux<CarResponseDto> findAll() {
        return carsRepository
                .findAll()
                .flatMap(car -> Flux.just(CarResponseMapper.map(car)));
    }

}
