package com.api.v1.exceptions.users;

public class DuplicatedSsnException extends RuntimeException {

    public DuplicatedSsnException(String ssn) {
        super("The input SSN %s is already registered.".formatted(ssn));
    }

}
