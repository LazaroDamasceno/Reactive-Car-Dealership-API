package com.api.v1.exceptions.users;

public class DuplicatedPersonalInformationException extends RuntimeException {

    public DuplicatedPersonalInformationException(String ssn, String email) {
        super("The input SSN %s or the input email %s is already registered.".formatted(ssn, email));
    }

}
