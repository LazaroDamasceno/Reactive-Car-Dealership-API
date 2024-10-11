package com.api.v1.domain.cars;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "car_inventory")
public class CarInventory {

    @Id
    private UUID id;
    private UUID carId;
    private int quantityAvailable;
    private int quantitySold;

}
