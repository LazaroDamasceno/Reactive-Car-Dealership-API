package com.api.v1.utils.purchases;

import com.api.v1.domain.purchases.Purchases;
import com.api.v1.domain.purchases.PurchasesRepository;
import com.api.v1.exceptions.purchases.PurchaseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Component
public class PurchaseFinderUtil {

    @Autowired
    private PurchasesRepository purchasesRepository;

    public Mono<Purchases> find(String orderNumber) {
        return purchasesRepository
                .findAll()
                .filter(e -> e.orderNumber().equals(new BigInteger(orderNumber)))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new PurchaseNotFoundException(orderNumber)));
    }

}
