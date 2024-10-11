package com.api.v2.users.services;

import com.api.v2.users.dtos.UserRegistrationRequestDto;
import com.api.v2.users.dtos.UserResponseDto;
import reactor.core.publisher.Mono;

public interface UserRegistrationService {

    Mono<UserResponseDto> register(UserRegistrationRequestDto requestDto);

}
