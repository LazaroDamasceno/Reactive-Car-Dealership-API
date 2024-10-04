package com.api.v1.domain.changes_records;

import com.api.v1.domain.cars.Cars;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Document(collection = "cars_changes_record")
public class CarsChangesRecord {

    @Id
    private UUID id;
    private Cars car;
    private Instant createdAt;
    private ZoneId createdAtZone;

    public CarsChangesRecord(Cars car) {
        this.id = UUID.randomUUID();
        this.car = car;
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }
}
