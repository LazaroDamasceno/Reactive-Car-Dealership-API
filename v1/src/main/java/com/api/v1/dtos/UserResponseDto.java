package com.api.v1.dtos;

import java.time.LocalDate;

public record UserResponseDto(
        String fullName,
        String ssn,
        LocalDate birthDate,
        String email,
        String gender,
        String phoneNumber
) {
}
