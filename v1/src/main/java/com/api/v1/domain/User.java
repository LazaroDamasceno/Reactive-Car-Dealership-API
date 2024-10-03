package com.api.v1.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "users")
@Getter
@Builder
public class User {

    @Id
    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;
    private LocalDate birthDate;
    private String email;
    private String gender;
    private String phoneNumber;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    public String getFullName() {
        if (middleName.isEmpty()) {
            return "%s %s".formatted(firstName, lastName);
        }
        return "%s %s %s".formatted(firstName, middleName, lastName);
    }

    public void modify(
            String firstName,
            String middleName,
            String lastName,
            LocalDate birthDate,
            String email,
            String gender,
            String phoneNumber
    ) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.modifiedAt = Instant.now();
        this.modifiedAtZone = ZoneId.systemDefault();
    }

}
