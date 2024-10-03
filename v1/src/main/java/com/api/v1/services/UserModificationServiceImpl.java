package com.api.v1.services;

import com.api.v1.domain.User;
import com.api.v1.domain.UserRepository;
import com.api.v1.dtos.UserModificationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserModificationServiceImpl implements UserModificationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> modify(@NotNull User user, @Valid UserModificationRequestDto requestDto) {
        user.modify(
                requestDto.firstName(),
                requestDto.middleName(),
                requestDto.lastName(),
                requestDto.birthDate(),
                requestDto.email(),
                requestDto.gender(),
                requestDto.phoneNumber()
        );
        return userRepository.save(user);
    }

}
