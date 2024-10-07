package com.api.v1.dtos.purchases;

import com.api.v1.dtos.cars.CarResponseDto;
import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.dtos.salespeople.SalespeopleResponseDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;

public record PurchaseResponseDto(
        CarResponseDto car,
        CustomerResponseDto customers,
        SalespeopleResponseDto salesperson,
        BigDecimal finalPrice,
        Instant createdAt,
        ZoneId createdAtZone
) {}
