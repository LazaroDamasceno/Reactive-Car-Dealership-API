package com.api.v1.services;

import com.api.v1.domain.User;
import com.api.v1.domain.UserRepository;
import com.api.v1.dtos.UserRegistrationRequestDto;
import com.api.v1.exceptions.DuplicatedSsnException;
import com.api.v1.utils.UserFinderUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

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
                       User newUser = User
                               .builder()
                               .id(UUID.randomUUID())
                               .firstName(requestDto.firstName())
                               .middleName(requestDto.middleName())
                               .lastName(requestDto.lastName())
                               .ssn(requestDto.ssn())
                               .birthDate(requestDto.birthDate())
                               .email(requestDto.email())
                               .gender(requestDto.gender())
                               .phoneNumber(requestDto.phoneNumber())
                               .createdAt(Instant.now())
                               .createdAtZone(ZoneId.systemDefault())
                               .build();
                       return userRepository.save(newUser);
                    });
                });
    }

}
