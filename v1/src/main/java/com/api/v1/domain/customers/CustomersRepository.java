package com.api.v1.domain.customers;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CustomersRepository extends ReactiveCrudRepository<Customers, UUID> {
}
