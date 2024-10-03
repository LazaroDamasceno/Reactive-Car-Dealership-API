package com.api.v1.domain.audit_trail;

import com.api.v1.domain.user.User;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "user_audit_trail")
@Getter
public class UserAuditTrail {

    @Id
    private UUID id;
    private User user;

    public UserAuditTrail(User user) {
        this.id = UUID.randomUUID();
        this.user = user;
    }
}
