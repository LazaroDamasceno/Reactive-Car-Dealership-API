package com.api.v1.dtos;

public record CustomerModificationRequestDto(
        String address,
        UserModificationRequestDto userModificationRequestDto
) {
}
