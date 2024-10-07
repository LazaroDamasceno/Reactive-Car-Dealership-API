package com.api.v1.services.users;

import com.api.v1.domain.users.Users;
import com.api.v1.domain.users.UsersRepository;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import com.api.v1.exceptions.users.DuplicatedSsnException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public Mono<Users> register(@Valid UserRegistrationRequestDto requestDto) {
        return userRepository
                .findAll()
                .filter(e -> e.getSsn().equals(requestDto.ssn()))
                .hasElements()
                .flatMap(exists -> {
                    if (exists) return Mono.error(new DuplicatedSsnException(requestDto.ssn()));
                    return Mono.defer(() -> {
                       Users newUser = Users.create(requestDto);
                       return userRepository.save(newUser);
                    });
                });
    }

}
