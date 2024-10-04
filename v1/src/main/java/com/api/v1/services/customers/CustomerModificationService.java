package com.api.v1.services.customers;

import com.api.v1.domain.customers.Customer;
import com.api.v1.dtos.customers.CustomerModificationRequestDto;
import reactor.core.publisher.Mono;

public interface CustomerModificationService {

    Mono<Customer> modify(String ssn, CustomerModificationRequestDto requestDto);

}
