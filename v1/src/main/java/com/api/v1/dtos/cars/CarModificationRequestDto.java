package com.api.v1.dtos.cars;

import jakarta.validation.constraints.NotBlank;

public record CarModificationRequestDto(
        @NotBlank String make,
        @NotBlank String model,
        int productionYear,
        double price
) {
}
