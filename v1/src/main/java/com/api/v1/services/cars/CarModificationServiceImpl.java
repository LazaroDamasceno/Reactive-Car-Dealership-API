package com.api.v1.services.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.domain.cars.CarsRepository;
import com.api.v1.domain.changes_records.CarsChangesRecord;
import com.api.v1.domain.changes_records.CarsChangesRecordRepository;
import com.api.v1.dtos.cars.CarModificationRequestDto;
import com.api.v1.dtos.cars.CarResponseDto;
import com.api.v1.utils.cars.CarFinderUtil;
import com.api.v1.utils.cars.CarResponseMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CarModificationServiceImpl implements CarModificationService {

    @Autowired
    private CarFinderUtil carFinderUtil;

    @Autowired
    private CarsChangesRecordRepository carsChangesRecordRepository;

    @Autowired
    private CarsRepository carsRepository;

    @Override
    public Mono<CarResponseDto> modify(
            @NotBlank @Size(min=13, max=13) String vin,
            @Valid CarModificationRequestDto requestDto
    ) {
        return carFinderUtil
                .find(vin)
                .flatMap(car -> {
                    CarsChangesRecord carsChangesRecord = CarsChangesRecord.create(car);
                    return carsChangesRecordRepository.save(carsChangesRecord);
                })
                .then(Mono.defer(() -> carFinderUtil
                        .find(vin)
                        .flatMap(car -> {
                            car.modify(requestDto);
                            return carsRepository.save(car);
                        })
                ))
                .flatMap(car -> Mono.just(CarResponseMapper.map(car)));
    }

}
