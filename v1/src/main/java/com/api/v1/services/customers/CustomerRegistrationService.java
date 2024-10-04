package com.api.v1.services.customers;

import com.api.v1.domain.customers.Customers;
import com.api.v1.dtos.customers.CustomerRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {

    Mono<Customers> register(CustomerRegistrationRequestDto requestDto);

}
