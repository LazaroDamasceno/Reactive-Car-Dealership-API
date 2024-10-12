package com.api.v2.users.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Table("user_audit_trail")
public record UserAuditTrail(
        @Id
        UUID id,
        String firstName,
        String middleName,
        String lastName,
        LocalDate birthDate,
        String ssn,
        String email,
        String gender,
        String phoneNumber,
        Instant createdAt,
        ZoneId createdAtZone
) {

    public static UserAuditTrail of(User user) {
        return new UserAuditTrail(
                null,
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getSsn(),
                user.getEmail(),
                user.getGender(),
                user.getPhoneNumber(),
                Instant.now(),
                ZoneId.systemDefault()
        );
    }

}
