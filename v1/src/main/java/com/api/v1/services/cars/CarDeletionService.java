package com.api.v1.services.cars;

import reactor.core.publisher.Mono;

public interface CarDeletionService {

    Mono<Void> deleteByVin(String vin);
    Mono<Void> deleteAll();

}
