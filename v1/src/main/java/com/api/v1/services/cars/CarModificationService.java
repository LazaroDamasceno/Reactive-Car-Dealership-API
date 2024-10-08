package com.api.v1.services.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.dtos.cars.CarModificationRequestDto;
import com.api.v1.dtos.cars.CarResponseDto;
import reactor.core.publisher.Mono;

public interface CarModificationService {

    Mono<CarResponseDto> modify(String vin, CarModificationRequestDto requestDto);

}
