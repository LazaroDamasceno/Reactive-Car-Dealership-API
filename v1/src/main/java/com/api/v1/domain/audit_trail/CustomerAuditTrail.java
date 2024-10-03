package com.api.v1.domain.audit_trail;

import com.api.v1.domain.customer.Customer;
import com.api.v1.domain.user.User;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "CustomerAuditTrail")
@Getter
public class CustomerAuditTrail {

    @Id
    private UUID id;
    private String address;
    private User user;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    public CustomerAuditTrail(Customer customer) {
        this.id = customer.getId();
        this.address = customer.getAddress();
        this.user = customer.getUser();
        this.createdAt = customer.getCreatedAt();
        this.createdAtZone = customer.getCreatedAtZone();
        this.modifiedAt = customer.getModifiedAt();
        this.modifiedAtZone = customer.getModifiedAtZone();
    }
}
