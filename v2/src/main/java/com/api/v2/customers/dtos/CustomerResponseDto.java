package com.api.v2.customers.dtos;

import com.api.v2.users.dtos.UserResponseDto;

public record CustomerResponseDto(
        String address,
        UserResponseDto userResponseDto
) {
}
