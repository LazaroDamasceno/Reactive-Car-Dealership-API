package com.api.v1.services.user;

import com.api.v1.domain.user.User;
import com.api.v1.dtos.UserRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface UserRegistrationService {

    Mono<User> register(UserRegistrationRequestDto requestDto);

}
