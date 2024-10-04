package com.api.v1.services.salespeople;

import com.api.v1.domain.changes_records.SalespeopleChangesRecord;
import com.api.v1.domain.changes_records.SalespeopleChangesRecordRepository;
import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.domain.salespeople.SalespeopleRepository;
import com.api.v1.domain.users.Users;
import com.api.v1.domain.users.UsersRepository;
import com.api.v1.dtos.users.UserModificationRequestDto;
import com.api.v1.services.users.UserModificationService;
import com.api.v1.utils.salespeople.SalespersonFinderUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class SalespersonModificationServiceImpl implements SalespersonModificationService {

    @Autowired
    private SalespersonFinderUtil salespersonFinderUtil;

    @Autowired
    private UserModificationService userModificationService;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private SalespeopleRepository salespersonRepository;

    @Autowired
    private SalespeopleChangesRecordRepository salespersonChangesRecordRepository;

    @Override
    public Mono<Salespeople> modify(
            @NotBlank @Size(min = 7, max = 7) String employeeId,
            @Valid UserModificationRequestDto requestDto
    ) {
        return salespersonFinderUtil
                .find(employeeId)
                .flatMap(salesperson -> userModificationService.modify(salesperson.getUser(), requestDto))
                .then(Mono.defer(() -> salespersonFinderUtil
                        .find(employeeId)
                        .flatMap(salesperson -> salespersonChangesRecordRepository
                                .save(new SalespeopleChangesRecord(salesperson))
                                .then(Mono.defer(() -> {
                                    Users user = salesperson.getUser();
                                    user.modify(requestDto);
                                    return userRepository.save(user)
                                            .flatMap(modifiedUser -> {
                                                salesperson.modify(user);
                                                return salespersonRepository.save(salesperson);
                                            });
                                })))));
    }
}