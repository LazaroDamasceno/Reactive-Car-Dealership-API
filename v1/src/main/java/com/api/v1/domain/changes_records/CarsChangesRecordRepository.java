package com.api.v1.domain.changes_records;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CarsChangesRecordRepository extends ReactiveCrudRepository<CarsChangesRecord, UUID> {
}
