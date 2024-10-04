package com.api.v1.services.users;

import com.api.v1.domain.users.User;
import com.api.v1.domain.users.UserRepository;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import com.api.v1.exceptions.users.DuplicatedSsnException;
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
