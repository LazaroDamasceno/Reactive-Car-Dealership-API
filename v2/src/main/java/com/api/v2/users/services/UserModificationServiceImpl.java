package com.api.v2.users.services;

import com.api.v2.users.domain.User;
import com.api.v2.users.domain.UserAuditTrail;
import com.api.v2.users.domain.UserAuditTrailRepository;
import com.api.v2.users.domain.UserRepository;
import com.api.v2.users.dtos.UserModificationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserModificationServiceImpl implements UserModificationService {

    @Autowired
    private UserAuditTrailRepository userAuditTrailRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> modify(@NotNull User user, @Valid UserModificationRequestDto requestDto) {
        return userAuditTrailRepository
                .save(UserAuditTrail.of(user))
                .then(Mono.defer(() -> {
                   user.modify(requestDto);
                   return userRepository.save(user);
                }));
    }

}
