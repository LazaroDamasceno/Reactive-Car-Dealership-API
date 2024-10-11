package com.api.v1.domain.cars;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "car_inventory")
@NoArgsConstructor
@Getter
public class CarInventory {

    @Id
    private UUID id;
    private Cars car;
    private int quantityAvailable;
    private int quantitySold;
    private Instant createdAt;
    private ZoneId createdAtZone;

    private CarInventory(Cars car, int quantityAvailable) {
        this.id = UUID.randomUUID();
        this.car = car;
        this.quantityAvailable = quantityAvailable;
        this.quantitySold = 0;
        this.createdAt = Instant.now();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static CarInventory create(Cars car, int quantityAvailable) {
        return new CarInventory(car, quantityAvailable);
    }

    public void change() {
        quantityAvailable -= 1;
        quantitySold += 1;
    }

}
