package com.api.v1.exceptions.cars;

public class DuplicatedVinException extends RuntimeException {

    public DuplicatedVinException(String vin) {
        super("The input vin %s is already registered.");
    }

}
