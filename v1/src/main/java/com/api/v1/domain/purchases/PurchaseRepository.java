package com.api.v1.domain.purchases;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PurchaseRepository extends ReactiveCrudRepository<Purchase, UUID> {
}
