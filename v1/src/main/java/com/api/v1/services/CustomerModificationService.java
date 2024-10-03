package com.api.v1.services;

import com.api.v1.domain.Customer;
import com.api.v1.dtos.CustomerModificationRequestDto;
import reactor.core.publisher.Mono;

public interface CustomerModificationService {

    Mono<Customer> modify(String ssn, CustomerModificationRequestDto requestDto);

}
