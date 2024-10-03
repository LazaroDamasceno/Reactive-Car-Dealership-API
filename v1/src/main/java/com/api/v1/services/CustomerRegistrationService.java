package com.api.v1.services;

import com.api.v1.domain.Customer;
import com.api.v1.dtos.CustomerRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {

    Mono<Customer> register(CustomerRegistrationRequestDto requestDto);

}
