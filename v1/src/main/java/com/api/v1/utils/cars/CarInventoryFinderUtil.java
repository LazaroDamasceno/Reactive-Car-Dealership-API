package com.api.v1.utils.cars;

import com.api.v1.domain.cars.CarInventory;
import com.api.v1.domain.cars.CarInventoryRepository;
import com.api.v1.domain.cars.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CarInventoryFinderUtil {

    @Autowired
    private CarInventoryRepository carInventoryRepository;

    public Mono<CarInventory> find(Cars car) {
        return carInventoryRepository
                .findAll()
                .filter(e -> e.getCar().equals(car))
                .single();
    }

}
