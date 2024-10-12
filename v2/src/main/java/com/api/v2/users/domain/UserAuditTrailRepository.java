package com.api.v2.users.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface UserAuditTrailRepository extends R2dbcRepository<UserAuditTrail, UUID> {
}
