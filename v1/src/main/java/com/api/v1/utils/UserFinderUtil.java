package com.api.v1.utils;

import com.api.v1.domain.User;
import com.api.v1.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserFinderUtil {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> find(String ssn) {
        return userRepository
                .findAll()
                .filter(e -> e.getSsn().equals(ssn))
                .singleOrEmpty();
    }

}
