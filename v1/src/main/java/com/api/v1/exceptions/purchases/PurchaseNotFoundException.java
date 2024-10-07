package com.api.v1.exceptions.purchases;

public class PurchaseNotFoundException extends RuntimeException {

    public PurchaseNotFoundException(String orderNumber) {
        super("Purchase which order number is %s was not found.".formatted(orderNumber));
    }


}
