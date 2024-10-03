package com.api.v1.domain.audit_trail;

import com.api.v1.domain.user.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface UserAuditTrailRepository extends ReactiveCrudRepository<User, UUID> {
}
