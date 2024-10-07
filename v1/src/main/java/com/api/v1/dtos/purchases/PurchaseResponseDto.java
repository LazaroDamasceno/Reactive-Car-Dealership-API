package com.api.v1.dtos.purchases;

import com.api.v1.dtos.cars.CarResponseDto;
import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.dtos.salespeople.SalespersonResponseDto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZonedDateTime;

public record PurchaseResponseDto(
        BigInteger idNumber,
        CarResponseDto car,
        CustomerResponseDto customers,
        SalespersonResponseDto salesperson,
        BigDecimal finalPrice,
        ZonedDateTime createdAt
) {}
