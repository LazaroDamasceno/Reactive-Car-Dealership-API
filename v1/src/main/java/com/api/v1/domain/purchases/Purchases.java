package com.api.v1.domain.purchases;

import com.api.v1.domain.cars.Cars;
import com.api.v1.domain.customers.Customers;
import com.api.v1.domain.salespeople.Salespeople;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "v1_purchases")
public record Purchases (
    @Id
    UUID id,
    Cars car,
    Customers customers,
    Salespeople salesperson,
    BigDecimal finalPrice,
    Instant createdAt,
    ZoneId createdAtZone
) {

    public static Purchases create(Cars car, Customers customers, Salespeople salesperson) {
        return new Purchases(
            UUID.randomUUID(),
            car,
            customers,
            salesperson,
            car.getPrice().multiply(BigDecimal.valueOf(1.2)),
            Instant.now(),
            ZoneId.systemDefault()
        );
    }

}
