package com.api.v1.dtos.customers;

import com.api.v1.dtos.users.UserResponseDto;

public record CustomerResponseDto(
        String address,
        UserResponseDto responseDto
) {
}
