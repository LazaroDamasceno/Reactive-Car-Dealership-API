package com.api.v2.users.services;

import com.api.v2.users.domain.User;
import com.api.v2.users.dtos.UserModificationRequestDto;
import reactor.core.publisher.Mono;

public interface UserModificationService {

    Mono<User> modify(User user, UserModificationRequestDto requestDto);

}
