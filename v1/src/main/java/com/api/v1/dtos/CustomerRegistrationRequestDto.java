package com.api.v1.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CustomerRegistrationRequestDto(
        @NotBlank String address,
        @Valid UserRegistrationRequestDto userRegistrationRequestDto
) {
}
