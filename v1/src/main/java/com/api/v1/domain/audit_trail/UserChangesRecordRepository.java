package com.api.v1.domain.audit_trail;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface UserChangesRecordRepository extends ReactiveCrudRepository<UserChangesRecord, UUID> {
}
