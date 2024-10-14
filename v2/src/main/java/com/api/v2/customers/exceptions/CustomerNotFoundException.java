package com.api.v2.customers.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String ssn) {
        super("Customer whose SSN %s was not found.".formatted(ssn));
    }

}
