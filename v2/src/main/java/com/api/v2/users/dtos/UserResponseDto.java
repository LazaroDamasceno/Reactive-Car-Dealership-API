package com.api.v2.users.dtos;

import java.time.LocalDate;

public record UserResponseDto(
        String fullName,
        LocalDate birthDate,
        String ssn,
        String email,
        String gender,
        String phoneNumber
) {
}
