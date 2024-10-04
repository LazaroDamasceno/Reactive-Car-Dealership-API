package com.api.v1.services.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.dtos.cars.CarRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface CarRegistrationService {

    Mono<Cars> register(CarRegistrationRequestDto requestDto);

}
