package com.api.v1.controllers.customers;

import com.api.v1.domain.customers.Customers;
import com.api.v1.dtos.customers.CustomerModificationRequestDto;
import com.api.v1.dtos.customers.CustomerRegistrationRequestDto;
import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.services.customers.CustomerModificationService;
import com.api.v1.services.customers.CustomerRegistrationService;
import com.api.v1.services.customers.CustomerRetrievalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/customers")
public class CustomersController {

    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    @Autowired
    private CustomerModificationService customerModificationService;

    @Autowired
    private CustomerRetrievalService customerRetrievalService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Customers> register(@Valid @RequestBody CustomerRegistrationRequestDto requestDto) {
        return customerRegistrationService.register(requestDto);
    }

    @PutMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Customers> modify(
            @PathVariable @NotBlank @Size(min=9, max=9) String ssn,
            @Valid @RequestBody CustomerModificationRequestDto requestDto
    ) {
        return customerModificationService.modify(ssn, requestDto);
    }

    @GetMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<CustomerResponseDto> findBySsn(@PathVariable @NotBlank @Size(min=9, max=9) String ssn) {
        return customerRetrievalService.findBySsn(ssn);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CustomerResponseDto> findAll() {
        return customerRetrievalService.findAll();
    }

}
