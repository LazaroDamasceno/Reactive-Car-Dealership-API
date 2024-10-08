package com.api.v1.domain.customers;

import com.api.v1.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "v1_customers")
@Getter
@NoArgsConstructor
public class Customers {

    @Id
    private UUID id;
    private String address;
    private Users user;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    private Customers(String address, Users user) {
        this.id = UUID.randomUUID();
        this.address = address;
        this.user = user;
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static Customers create(String address, Users user) {
        return new Customers(address, user);
    }

    public void modify(String address, Users user) {
        this.address = address;
        this.user = user;
        modifiedAt = Instant.now();
        modifiedAtZone = ZoneId.systemDefault();
    }

}
