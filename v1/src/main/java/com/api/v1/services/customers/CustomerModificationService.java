package com.api.v1.services.customers;

import com.api.v1.dtos.customers.CustomerModificationRequestDto;
import com.api.v1.dtos.customers.CustomerResponseDto;
import reactor.core.publisher.Mono;

public interface CustomerModificationService {

    Mono<CustomerResponseDto> modify(String ssn, CustomerModificationRequestDto requestDto);

}
