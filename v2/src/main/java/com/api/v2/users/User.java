package com.api.v2.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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

}
