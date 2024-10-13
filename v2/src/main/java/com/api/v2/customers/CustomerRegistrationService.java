package com.api.v2.customers;

import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {

    Mono<CustomerResponseDto> register(CustomerRegistrationRequestDto requestDto);

}
