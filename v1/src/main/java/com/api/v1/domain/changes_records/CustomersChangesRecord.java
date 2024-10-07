package com.api.v1.domain.changes_records;

import com.api.v1.domain.customers.Customers;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "customers_changes_record")
public record CustomersChangesRecord (
    @Id
    UUID id,
    Customers customer,
    Instant createdAt,
    ZoneId createdAtZone
) {

    public static CustomersChangesRecord create(Customers customer) {
        return new CustomersChangesRecord(
                UUID.randomUUID(),
                customer,
                Instant.now(),
                ZoneId.systemDefault()
        );
    }

}
