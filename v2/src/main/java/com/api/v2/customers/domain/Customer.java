package com.api.v2.customers.domain;

import com.api.v2.customers.dtos.CustomerModificationRequestDto;
import com.api.v2.users.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "v2_customers")
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    private UUID id;
    private String address;
    private User user;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    private Customer(String address, User user) {
        this.id = UUID.randomUUID();
        this.address = address;
        this.user = user;
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static Customer of(String address, User user) {
        return new Customer(address, user);
    }

    public void modify(String address, User user) {
        this.address = address;
        this.user = user;
        modifiedAt = Instant.now();
        modifiedAtZone = ZoneId.systemDefault();
    }

}
