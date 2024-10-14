package com.api.v2.customers.controllers;

import com.api.v2.customers.services.CustomerRegistrationService;
import com.api.v2.customers.dtos.CustomerResponseDto;
import com.api.v2.customers.dtos.CustomerRegistrationRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/customers")
public class CustomerController {

    private final CustomerRegistrationService customerRegistrationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<CustomerResponseDto> register(@Valid @RequestBody CustomerRegistrationRequestDto requestDto) {
        return customerRegistrationService.register(requestDto);
    }

}
