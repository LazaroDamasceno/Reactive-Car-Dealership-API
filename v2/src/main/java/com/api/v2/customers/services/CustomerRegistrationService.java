package com.api.v2.customers.services;

import com.api.v2.customers.dtos.CustomerRegistrationRequestDto;
import com.api.v2.customers.dtos.CustomerResponseDto;
import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {

    Mono<CustomerResponseDto> register(CustomerRegistrationRequestDto requestDto);

}
