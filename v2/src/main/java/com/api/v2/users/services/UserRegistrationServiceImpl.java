package com.api.v2.users.services;

import com.api.v2.users.UserResponseMapper;
import com.api.v2.users.domain.User;
import com.api.v2.users.domain.UserRepository;
import com.api.v2.users.dtos.UserRegistrationRequestDto;
import com.api.v2.users.dtos.UserResponseDto;
import com.api.v2.users.exceptions.DuplicatedEmailException;
import com.api.v2.users.exceptions.DuplicatedSsnException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserResponseDto> register(@Valid UserRegistrationRequestDto requestDto) {
        return userRepository
                .findAll()
                .filter(user -> user.getSsn().equals(requestDto.ssn()))
                .hasElements()
                .flatMap(ssnExists -> {
                    if (ssnExists) return Mono.error(DuplicatedSsnException::new);
                    return userRepository
                            .findAll()
                            .filter(user -> user.getEmail().equals(requestDto.email()))
                            .hasElements()
                            .flatMap(emailExists -> {
                                if (emailExists) return Mono.error(DuplicatedEmailException::new);
                                return userRepository
                                        .save(User.of(requestDto))
                                        .flatMap(user -> Mono.just(UserResponseMapper.map(user)));
                            });
                });
    }

}
