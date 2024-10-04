package com.api.v1.services.users;

import com.api.v1.domain.users.Users;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface UserRegistrationService {

    Mono<Users> register(UserRegistrationRequestDto requestDto);

}
