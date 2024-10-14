package com.api.v2.customers.services;

import com.api.v2.customers.dtos.CustomerModificationRequestDto;
import com.api.v2.customers.dtos.CustomerResponseDto;
import reactor.core.publisher.Mono;

public interface CustomerModificationService {

    Mono<CustomerResponseDto> modify(String ssn, CustomerModificationRequestDto requestDto);

}
