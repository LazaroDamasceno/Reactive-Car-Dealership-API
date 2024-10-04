package com.api.v1.domain.cars;

import com.api.v1.dtos.cars.CarRegistrationRequestDto;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Document(collection = "v1_cars")
public class Cars {

    @Id
    private UUID id;
    private String make;
    private String model;
    private String vin;
    private int productionYear;
    private BigDecimal price;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    public Cars(CarRegistrationRequestDto requestDto) {
        this.id = UUID.randomUUID();
        this.make = requestDto.make();
        this.model = requestDto.model();
        this.vin = requestDto.vin();
        this.productionYear = requestDto.productionYear();
        this.price = BigDecimal.valueOf(requestDto.price());
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }
}
