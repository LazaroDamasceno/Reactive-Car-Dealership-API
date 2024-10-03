package com.api.v1.domain.audit_trail;

import com.api.v1.domain.user.User;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "salesperson_changes_record")
@Getter
public class SalespersonChangesRecord {

    @Id
    private UUID id;
    private User user;

    public SalespersonChangesRecord(User user) {
        this.id = UUID.randomUUID();
        this.user = user;
    }
}
