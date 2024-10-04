package com.api.v1.dtos.customers;

import com.api.v1.dtos.users.UserModificationRequestDto;

public record CustomerModificationRequestDto(
        String address,
        UserModificationRequestDto userModificationRequestDto
) {
}
