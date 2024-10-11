package com.api.v2.users.exceptions;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException() {
        super("The given email is already registered.");
    }
}
