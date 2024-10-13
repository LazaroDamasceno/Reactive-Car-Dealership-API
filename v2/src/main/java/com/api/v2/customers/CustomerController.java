package com.api.v2.customers;

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
