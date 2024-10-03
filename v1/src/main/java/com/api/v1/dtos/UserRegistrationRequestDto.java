package com.api.v1.dtos;

import java.time.LocalDate;

public record UserRegistrationRequestDto(
        String firstName,
        String middleName,
        String lastName,
        String ssn,
        LocalDate birthDate,
        String email,
        String gender,
        String phoneNumber
) {
}
