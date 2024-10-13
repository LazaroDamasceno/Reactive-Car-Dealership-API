package com.api.v2.users.domain;

import com.api.v2.users.dtos.UserModificationRequestDto;
import com.api.v2.users.dtos.UserRegistrationRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Document(collection = "v2_users")
public class User {

    @Id
    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String ssn;
    private String email;
    private String gender;
    private String phoneNumber;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    private User(UserRegistrationRequestDto requestDto) {
        this.id = UUID.randomUUID();
        this.firstName = requestDto.firstName();
        this.middleName = requestDto.middleName();
        this.lastName = requestDto.lastName();
        this.birthDate = requestDto.birthDate();
        this.ssn = requestDto.ssn();
        this.email = requestDto.email();
        this.gender = requestDto.gender();
        this.phoneNumber = requestDto.phoneNumber();
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static User of(UserRegistrationRequestDto requestDto) {
        return new User(requestDto);
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

    public String getFullName() {
        if (middleName.isEmpty()) {
            return "%s %s".formatted(firstName, lastName);
        }
        return "%s %s %s".formatted(firstName, middleName, lastName);
    }

}
