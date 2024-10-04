package com.api.v1.services.users;

import com.api.v1.domain.users.Users;
import com.api.v1.dtos.users.UserModificationRequestDto;
import reactor.core.publisher.Mono;

public interface UserModificationService {

    Mono<Users> modify(Users user, UserModificationRequestDto requestDto);

}
