package com.api.v1.controllers;

import com.api.v1.domain.Customer;
import com.api.v1.dtos.CustomerModificationRequestDto;
import com.api.v1.dtos.CustomerRegistrationRequestDto;
import com.api.v1.services.CustomerModificationService;
import com.api.v1.services.CustomerRegistrationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    @Autowired
    private CustomerModificationService customerModificationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Customer> register(@Valid @RequestBody CustomerRegistrationRequestDto requestDto) {
        return customerRegistrationService.register(requestDto);
    }

    @PutMapping("{ssn}")
    public Mono<Customer> modify(
            @PathVariable @NotBlank @Size(min=9, max=9) String ssn,
            @Valid @RequestBody CustomerModificationRequestDto requestDto
    ) {
        return customerModificationService.modify(ssn, requestDto);
    }

}
