package com.api.v1.dtos.customers;

import com.api.v1.dtos.users.UserRegistrationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CustomerRegistrationRequestDto(
        @NotBlank String address,
        @Valid UserRegistrationRequestDto userRegistrationRequestDto
) {
}
