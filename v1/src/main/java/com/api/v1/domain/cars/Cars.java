package com.api.v1.domain.cars;

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
    private String name;
    private String model;
    private String vin;
    private int productionYear;
    private BigDecimal price;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    public Cars(
            String name,
            String model,
            String vin,
            int productionYear,
            BigDecimal price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.model = model;
        this.vin = vin;
        this.productionYear = productionYear;
        this.price = price;
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }
}
