package com.api.v1.domain.purchases;

import com.api.v1.domain.cars.Cars;
import com.api.v1.domain.customers.Customers;
import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.utils.purchases.PurchaseIdGeneratorUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "v1_purchases")
public record Purchases (
    @Id
    UUID id,
    BigInteger idNumber,
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
                PurchaseIdGeneratorUtil.generate(),
                car,
                customers,
                salesperson,
                car.getPrice().multiply(BigDecimal.valueOf(1.2)),
                Instant.now(),
                ZoneId.systemDefault()
        );
    }

}
