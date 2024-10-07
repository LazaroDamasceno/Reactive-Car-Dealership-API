package com.api.v1.services.users;

import com.api.v1.domain.changes_records.UsersChangesRecord;
import com.api.v1.domain.users.Users;
import com.api.v1.domain.changes_records.UsersChangesRecordRepository;
import com.api.v1.domain.users.UsersRepository;
import com.api.v1.dtos.users.UserModificationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserModificationServiceImpl implements UserModificationService {

    @Autowired
    private UsersChangesRecordRepository userChangesRecordRepository;

    @Autowired
    private UsersRepository userRepository;

    @Override
    public Mono<Users> modify(@NotNull Users user, @Valid UserModificationRequestDto requestDto) {
        return userChangesRecordRepository.save(UsersChangesRecord.create(user))
                        .then(Mono.defer(() -> {
                            user.modify(requestDto);
                            return userRepository.save(user);
                        }));
    }

}
