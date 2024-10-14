package com.api.v2.customers.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CustomerAuditTrailRepository extends ReactiveCrudRepository<CustomerAuditTrail, UUID> {
}
