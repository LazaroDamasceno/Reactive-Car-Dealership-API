package com.api.v1.domain.changes_records;

import com.api.v1.domain.salespeople.Salespeople;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "salespeople_changes_record")
@Getter
public class SalespeopleChangesRecord {

    @Id
    private UUID id;
    private Salespeople salesperson;
    private Instant createdAt;
    private ZoneId createdAtZone;

    public SalespeopleChangesRecord(Salespeople salesperson) {
        this.id = UUID.randomUUID();
        this.salesperson = salesperson;
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }
}
