package com.api.v1.dtos.salespeople;

import com.api.v1.dtos.users.UserResponseDto;

import java.math.BigInteger;

public record SalespeopleResponseDto(
        BigInteger employeeId,
        UserResponseDto user
) {
}
