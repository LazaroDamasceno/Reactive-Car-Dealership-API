package com.api.v1.domain.users;

import com.api.v1.dtos.users.UserModificationRequestDto;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "v1_users")
@Getter
@NoArgsConstructor
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

    public User(UserRegistrationRequestDto requestDto) {
        this.id = UUID.randomUUID();
        this.firstName = requestDto.firstName();
        this.middleName = requestDto.middleName();
        this.lastName = requestDto.lastName();
        this.ssn = requestDto.ssn();
        this.birthDate = requestDto.birthDate();
        this.email = requestDto.email();
        this.gender = requestDto.gender();
        this.phoneNumber = requestDto.phoneNumber();
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public String getFullName() {
        if (middleName.isEmpty()) {
            return "%s %s".formatted(firstName, lastName);
        }
        return "%s %s %s".formatted(firstName, middleName, lastName);
    }

    public void modify(UserModificationRequestDto requestDto) {
        this.firstName = requestDto.firstName();
        this.middleName = requestDto.middleName();
        this.lastName = requestDto.lastName();
        this.birthDate = requestDto.birthDate();
        this.email = requestDto.email();
        this.gender = requestDto.gender();
        this.phoneNumber = requestDto.phoneNumber();
        this.modifiedAt = Instant.now();
        this.modifiedAtZone = ZoneId.systemDefault();
    }

}
