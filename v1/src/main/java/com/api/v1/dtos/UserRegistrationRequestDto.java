package com.api.v1.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRegistrationRequestDto(
        @NotBlank String firstName,
        String middleName,
        @NotBlank String lastName,
        @NotBlank @Size(min=9, max=9) String ssn,
        @NotNull LocalDate birthDate,
        @NotBlank @Email String email,
        @NotBlank @Size(min=1) String gender,
        @NotBlank @Size(min=10, max=10) String phoneNumber
) {
}
