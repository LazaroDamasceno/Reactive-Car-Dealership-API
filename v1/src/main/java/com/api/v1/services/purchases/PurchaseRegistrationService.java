package com.api.v1.services.purchases;

import com.api.v1.domain.purchases.Purchases;
import reactor.core.publisher.Mono;

public interface PurchaseRegistrationService {

    Mono<Purchases> register(String vin, String ssn);

}
