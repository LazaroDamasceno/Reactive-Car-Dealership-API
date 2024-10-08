package com.api.v1.services.purchases;

import reactor.core.publisher.Mono;

public interface PurchaseDeletionService {

    Mono<Void> deleteAll();
    Mono<Void> deleteByOrderNumber(String orderNumber);

}
