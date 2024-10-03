package com.api.v1.exceptions;

public class SalespersonNotFoundException extends RuntimeException {

    public SalespersonNotFoundException(String employeeId) {
        super("There's no salesperson whose id is %s".formatted(employeeId));
    }

}
