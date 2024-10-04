package com.api.v1.dtos.cars;

import jakarta.validation.constraints.NotBlank;

public record CarRegistrationRequestDto(
        @NotBlank String name,
        @NotBlank String model,
        String vin,
        int productionYear,
        double price
) {
}
