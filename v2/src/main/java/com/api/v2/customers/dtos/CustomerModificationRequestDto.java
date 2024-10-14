package com.api.v2.customers.dtos;

import com.api.v2.users.dtos.UserModificationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CustomerModificationRequestDto(
        @NotBlank String address,
        @Valid UserModificationRequestDto userModificationRequestDto
) {
}
