package com.api.v1.exceptions.cars;

public class UnavailableCarException extends RuntimeException {

    public UnavailableCarException() {
        super("The request product is currently unavailable.");
    }

}
