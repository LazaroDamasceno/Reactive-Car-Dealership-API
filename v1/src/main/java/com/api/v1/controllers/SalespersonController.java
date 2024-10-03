package com.api.v1.controllers;

import com.api.v1.domain.salesperson.Salesperson;
import com.api.v1.dtos.UserRegistrationRequestDto;
import com.api.v1.services.salesperson.SalespersonRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/salesperson")
public class SalespersonController {

    @Autowired
    private SalespersonRegistrationService salespersonRegistrationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Salesperson> register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return salespersonRegistrationService.register(requestDto);
    }

}
