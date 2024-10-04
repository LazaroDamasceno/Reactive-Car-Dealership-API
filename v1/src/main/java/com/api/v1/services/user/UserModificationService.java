package com.api.v1.services.user;

import com.api.v1.domain.user.User;
import com.api.v1.dtos.user.UserModificationRequestDto;
import reactor.core.publisher.Mono;

public interface UserModificationService {

    Mono<User> modify(User user, UserModificationRequestDto requestDto);

}
