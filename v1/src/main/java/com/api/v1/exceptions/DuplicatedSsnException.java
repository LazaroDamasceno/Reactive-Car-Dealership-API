package com.api.v1.exceptions;

public class DuplicatedSsnException extends RuntimeException {

    public DuplicatedSsnException(String ssn) {
        super("The input SSN %s is already registered.");
    }

}
