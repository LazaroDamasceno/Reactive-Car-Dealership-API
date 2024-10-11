package com.api.v2.users;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface UserAuditTrailRepository extends ReactiveCrudRepository<UserAuditTrail, UUID> {
}
