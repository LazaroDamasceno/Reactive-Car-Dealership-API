package com.api.v1.domain.cars;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CarInventoryRepository extends ReactiveCrudRepository<CarInventory, UUID> {
}
