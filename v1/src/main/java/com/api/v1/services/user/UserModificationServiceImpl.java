package com.api.v1.services.user;

import com.api.v1.domain.audit_trail.UserAuditTrail;
import com.api.v1.domain.user.User;
import com.api.v1.domain.audit_trail.UserChangesRecordRepository;
import com.api.v1.domain.user.UserRepository;
import com.api.v1.dtos.UserModificationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserModificationServiceImpl implements UserModificationService {

    @Autowired
    private UserChangesRecordRepository auditTrailRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> modify(@NotNull User user, @Valid UserModificationRequestDto requestDto) {
        return auditTrailRepository.save(new UserAuditTrail(user))
                        .then(Mono.defer(() -> {
                            user.modify(requestDto);
                            return userRepository.save(user);
                        }));
    }

}
