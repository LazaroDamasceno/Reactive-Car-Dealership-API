package com.api.v2.customers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "customer_audit_trail")
public record CustomerAuditTrail(

    @Id
    UUID id,
    Customer customer,
    Instant createAt,
    ZoneId createdAtZone
) {

    public static CustomerAuditTrail of(Customer customer) {
        return new CustomerAuditTrail(
                UUID.randomUUID(),
                customer,
                Instant.now(),
                ZoneId.systemDefault()
        );
    }

}
