package com.api.v2.customers.dtos;

import com.api.v2.users.dtos.UserRegistrationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CustomerRegistrationRequestDto(
        @NotBlank String address,
        @Valid UserRegistrationRequestDto userRegistrationRequestDto
) {
}
