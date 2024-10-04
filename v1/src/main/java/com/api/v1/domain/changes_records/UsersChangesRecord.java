package com.api.v1.domain.changes_records;

import com.api.v1.domain.users.Users;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "user_changes_Record")
@Getter
public class UsersChangesRecord {

    @Id
    private UUID id;
    private Users user;

    public UsersChangesRecord(Users user) {
        this.id = UUID.randomUUID();
        this.user = user;
    }
}
