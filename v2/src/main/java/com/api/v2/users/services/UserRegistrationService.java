package com.api.v2.users.services;

import com.api.v2.users.domain.User;
import com.api.v2.users.dtos.UserRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface UserRegistrationService {

    Mono<User> register(UserRegistrationRequestDto requestDto);

}
