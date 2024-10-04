package com.api.v1.dtos.customer;

import com.api.v1.dtos.user.UserRegistrationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CustomerRegistrationRequestDto(
        @NotBlank String address,
        @Valid UserRegistrationRequestDto userRegistrationRequestDto
) {
}
