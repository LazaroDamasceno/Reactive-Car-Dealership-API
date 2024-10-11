package com.api.v2.users;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Document(collection = "user_audit_trail")
public record UserAuditTrail(
    @Id
    UUID id,
    User user,
    Instant createdAt,
    ZoneId createdAtZone
) {

    public static UserAuditTrail of(User user) {
        return new UserAuditTrail(
                UUID.randomUUID(),
                user,
                Instant.now(),
                ZoneId.systemDefault()
        );
    }

}
