package com.api.v1.services.salespeople;

import com.api.v1.domain.changes_records.SalespeopleChangesRecord;
import com.api.v1.domain.changes_records.SalespeopleChangesRecordRepository;
import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.domain.salespeople.SalespeopleRepository;
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
                .flatMap(employee -> userModificationService.modify(employee.getUser(), requestDto)
                        .flatMap(user -> {
                            SalespeopleChangesRecord salespeopleChangesRecord = SalespeopleChangesRecord.create(employee);
                            return salespersonChangesRecordRepository.save(salespeopleChangesRecord)
                                    .then(Mono.defer(() -> {
                                        employee.modify(user);
                                        return salespersonRepository.save(employee);
                                    }));
                        }));
    }
}