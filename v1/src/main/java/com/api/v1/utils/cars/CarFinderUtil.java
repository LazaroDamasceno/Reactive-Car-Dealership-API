package com.api.v1.utils.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.domain.cars.CarsRepository;
import com.api.v1.exceptions.cars.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CarFinderUtil {

    @Autowired
    private CarsRepository carsRepository;

    public Mono<Cars> find(String vin) {
        return carsRepository
                .findAll()
                .filter(e -> e.getVin().equals(vin))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new CarNotFoundException(vin)));
    }

}
