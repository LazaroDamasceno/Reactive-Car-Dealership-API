package com.api.v1.domain.cars;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CarsRepository extends ReactiveCrudRepository<Cars, UUID> {
}
