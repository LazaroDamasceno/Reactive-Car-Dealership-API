package com.api.v1.services.cars;

import com.api.v1.dtos.cars.CarResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarRetrievalService {

    Mono<CarResponseDto> findByVin(String vin);
    Flux<CarResponseDto> findAll();

}
