package com.api.v1.services.user;

import com.api.v1.domain.user.User;
import com.api.v1.domain.user.UserRepository;
import com.api.v1.dtos.UserRegistrationRequestDto;
import com.api.v1.exceptions.DuplicatedSsnException;
import com.api.v1.utils.UserFinderUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFinderUtil userFinderUtil;

    @Override
    public Mono<User> register(@Valid UserRegistrationRequestDto requestDto) {
        return userFinderUtil
                .find(requestDto.ssn())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) return Mono.error(new DuplicatedSsnException(requestDto.ssn()));
                    return Mono.defer(() -> {
                       User newUser = User.createInstance(requestDto);
                       return userRepository.save(newUser);
                    });
                });
    }

}
