package com.api.v1.domain.audit_trail;

import com.api.v1.domain.customer.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CustomerAuditTrailRepository extends ReactiveCrudRepository<CustomerAuditTrail, UUID> {
}
