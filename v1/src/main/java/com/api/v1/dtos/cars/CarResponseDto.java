package com.api.v1.dtos.cars;

import java.math.BigDecimal;

public record CarResponseDto(
        String make,
        String model,
        String vin,
        int productionYear,
        BigDecimal price
) {
}
