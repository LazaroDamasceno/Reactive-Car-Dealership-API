package com.api.v1.domain.changes_records;

import com.api.v1.domain.salespeople.Salespeople;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "salespeople_changes_record")
public record SalespeopleChangesRecord (
    @Id
    UUID id,
    Salespeople salesperson,
    Instant createdAt,
    ZoneId createdAtZone,
    Instant deletionDate
) {

    public static SalespeopleChangesRecord create(Salespeople salesperson) {
        return new SalespeopleChangesRecord(
                UUID.randomUUID(),
                salesperson,
                Instant.now(),
                ZoneId.systemDefault(),
                Instant.now().plusSeconds(157_680_000L)
        );
    }

}
