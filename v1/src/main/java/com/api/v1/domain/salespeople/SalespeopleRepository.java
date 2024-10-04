package com.api.v1.domain.salespeople;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface SalespeopleRepository extends ReactiveCrudRepository<Salespeople, UUID> {
}
