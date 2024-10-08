package com.api.v1.services.purchases;

import com.api.v1.domain.purchases.Purchases;
import com.api.v1.dtos.purchases.PurchaseResponseDto;
import reactor.core.publisher.Mono;

public interface PurchaseRegistrationService {

    Mono<PurchaseResponseDto> register(String vin, String ssn, String employeeId);

}
