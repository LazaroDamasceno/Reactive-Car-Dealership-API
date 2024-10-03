package com.api.v1.domain.audit_trail;

import com.api.v1.domain.user.User;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "UserAuditTrail")
@Getter
public class UserAuditTrail {

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

    public UserAuditTrail(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.ssn = user.getSsn();
        this.birthDate = user.getBirthDate();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
        this.createdAt = user.getCreatedAt();
        this.createdAtZone = user.getCreatedAtZone();
        this.modifiedAt = user.getModifiedAt();
        this.modifiedAtZone = user.getModifiedAtZone();
    }
}
