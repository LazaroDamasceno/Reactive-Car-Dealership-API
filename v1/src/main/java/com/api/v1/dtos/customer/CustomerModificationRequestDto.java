package com.api.v1.dtos.customer;

import com.api.v1.dtos.user.UserModificationRequestDto;

public record CustomerModificationRequestDto(
        String address,
        UserModificationRequestDto userModificationRequestDto
) {
}
