package com.api.v1.exceptions.cars;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String vin) {
        super("Car whose VIN is %s was not found.".formatted(vin));
    }

}
