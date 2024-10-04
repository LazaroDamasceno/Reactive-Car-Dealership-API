package com.api.v1.services.user;

import com.api.v1.domain.user.User;
import com.api.v1.domain.user.UserRepository;
import com.api.v1.dtos.user.UserRegistrationRequestDto;
import com.api.v1.exceptions.user.DuplicatedSsnException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> register(@Valid UserRegistrationRequestDto requestDto) {
        return userRepository
                .findAll()
                .filter(e -> e.getSsn().equals(requestDto.ssn()))
                .hasElements()
                .flatMap(exists -> {
                    if (exists) return Mono.error(new DuplicatedSsnException(requestDto.ssn()));
                    return Mono.defer(() -> {
                       User newUser = new User(requestDto);
                       return userRepository.save(newUser);
                    });
                });
    }

}
