package com.api.v1.services.purchases;

import com.api.v1.dtos.purchases.PurchaseResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurchaseRetrievalService {

    Flux<PurchaseResponseDto> findBySalesperson(String employeeId);
    Flux<PurchaseResponseDto> findByCustomer(String ssn);
    Mono<PurchaseResponseDto> findByOrderNumber(String orderNumber);
    Flux<PurchaseResponseDto> findAll();

}
