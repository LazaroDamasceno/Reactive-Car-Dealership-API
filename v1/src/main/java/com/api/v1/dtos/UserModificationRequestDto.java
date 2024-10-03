package com.api.v1.dtos;

import java.time.LocalDate;

public record UserModificationRequestDto(
        String firstName,
        String middleName,
        String lastName,
        LocalDate birthDate,
        String email,
        String gender,
        String phoneNumber
) {
}
