package com.api.v1.services.customer;

import com.api.v1.domain.customer.Customer;
import com.api.v1.dtos.CustomerRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {

    Mono<Customer> register(CustomerRegistrationRequestDto requestDto);

}
