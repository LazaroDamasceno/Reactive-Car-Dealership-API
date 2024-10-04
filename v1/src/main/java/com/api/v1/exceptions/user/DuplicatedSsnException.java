package com.api.v1.exceptions.user;

public class DuplicatedSsnException extends RuntimeException {

    public DuplicatedSsnException(String ssn) {
        super("The input SSN %s is already registered.".formatted(ssn));
    }

}
