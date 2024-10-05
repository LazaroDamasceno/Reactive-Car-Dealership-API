package com.api.v1.domain.purchases;

import com.api.v1.domain.cars.Cars;
import com.api.v1.domain.customers.Customers;
import com.api.v1.domain.salespeople.Salespeople;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "v1_purchases")
@Getter
@NoArgsConstructor
public class Purchases {

    @Id
    private UUID id;
    private Cars car;
    private Customers customers;
    private Salespeople salesperson;
    private BigDecimal price;
    private BigDecimal finalPrice;
    private Instant createdAt;
    private ZoneId createdAtZone;

    public Purchases(Cars car, Customers customers, Salespeople salesperson) {
        this.id = UUID.randomUUID();
        this.car = car;
        this.customers = customers;
        this.salesperson = salesperson;
        this.price = car.getPrice();
        this.finalPrice = car.getPrice().multiply(BigDecimal.valueOf(1.2));
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }
}
