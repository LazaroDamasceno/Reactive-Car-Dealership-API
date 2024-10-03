package com.api.v1.controllers;

import com.api.v1.domain.User;
import com.api.v1.dtos.UserRegistrationRequestDto;
import com.api.v1.services.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<User> register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userRegistrationService.register(requestDto);
    }

}
