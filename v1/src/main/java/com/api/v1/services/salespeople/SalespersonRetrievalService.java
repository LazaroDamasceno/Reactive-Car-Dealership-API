package com.api.v1.services.salespeople;

import com.api.v1.dtos.salespeople.SalespersonResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SalespersonRetrievalService {

    Flux<SalespersonResponseDto> findAll();
    Mono<SalespersonResponseDto> findByEmployeeId(String employeeId);

}
