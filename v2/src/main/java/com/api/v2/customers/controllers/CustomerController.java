package com.api.v2.customers.controllers;

import com.api.v2.customers.dtos.CustomerModificationRequestDto;
import com.api.v2.customers.services.CustomerModificationService;
import com.api.v2.customers.services.CustomerRegistrationService;
import com.api.v2.customers.dtos.CustomerResponseDto;
import com.api.v2.customers.dtos.CustomerRegistrationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/customers")
public class CustomerController {

    private final CustomerRegistrationService customerRegistrationService;
    private final CustomerModificationService customerModificationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<CustomerResponseDto> register(@Valid @RequestBody CustomerRegistrationRequestDto requestDto) {
        return customerRegistrationService.register(requestDto);
    }

    @PutMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<CustomerResponseDto> modify(
            @PathVariable @NotBlank @Size(min=9, max=9) String ssn,
            @RequestBody @Valid CustomerModificationRequestDto requestDto
    ) {
        return customerModificationService.modify(ssn, requestDto);
    }

}
