package com.api.v1.services.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.dtos.cars.CarRegistrationRequestDto;
import com.api.v1.dtos.cars.CarResponseDto;
import reactor.core.publisher.Mono;

public interface CarRegistrationService {

    Mono<CarResponseDto> register(CarRegistrationRequestDto requestDto);

}
