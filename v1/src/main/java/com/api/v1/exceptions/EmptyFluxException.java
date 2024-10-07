package com.api.v1.exceptions;

public class EmptyFluxException extends RuntimeException {

    public EmptyFluxException() {
        super("The flux is empty.");
    }

}
