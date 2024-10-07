package com.api.v1.domain.changes_records;

import com.api.v1.domain.users.Users;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "users_changes_Record")
public record UsersChangesRecord (
    @Id
    UUID id,
    Users user,
    Instant createdAt,
    ZoneId createdAtZone
) {

    public static UsersChangesRecord create(Users user) {
        return new UsersChangesRecord(
            UUID.randomUUID(),
            user,
            Instant.now(),
            ZoneId.systemDefault()
        );
    }

}
