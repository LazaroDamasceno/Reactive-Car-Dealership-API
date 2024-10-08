package com.api.v1.domain.changes_records;

import com.api.v1.domain.cars.Cars;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "cars_changes_record")
public record CarsChangesRecord (
    @Id
    UUID id,
    Cars car,
    Instant createdAt,
    ZoneId createdAtZone,
    Instant deletionDate
) {

    public static CarsChangesRecord create(Cars car) {
        return new CarsChangesRecord(
                UUID.randomUUID(),
                car,
                Instant.now(),
                ZoneId.systemDefault(),
                Instant.now().plusSeconds(157_680_000L)
        );
    }

}
