package com.api.v1.services;

import com.api.v1.domain.User;
import com.api.v1.dtos.UserModificationRequestDto;
import reactor.core.publisher.Mono;

public interface UserModificationService {

    Mono<User> modify(User user, UserModificationRequestDto requestDto);

}
