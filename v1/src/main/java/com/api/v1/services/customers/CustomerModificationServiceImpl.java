package com.api.v1.services.customers;

import com.api.v1.domain.changes_records.CustomersChangesRecord;
import com.api.v1.domain.customers.Customers;
import com.api.v1.domain.changes_records.CustomersChangesRecordRepository;
import com.api.v1.domain.customers.CustomersRepository;
import com.api.v1.dtos.customers.CustomerModificationRequestDto;
import com.api.v1.services.users.UserModificationService;
import com.api.v1.utils.customers.CustomerFinderUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CustomerModificationServiceImpl implements CustomerModificationService {

    @Autowired
    private UserModificationService userModificationService;

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Autowired
    private CustomersChangesRecordRepository auditTrailRepository;

    @Autowired
    private CustomersRepository customerRepository;

    @Override
    public Mono<Customers> modify(
            @NotBlank @Size(min = 9, max = 9) String ssn,
            @Valid CustomerModificationRequestDto requestDto
    ) {
        return customerFinderUtil
                .find(ssn)
                .flatMap(customer -> auditTrailRepository.save(CustomersChangesRecord.create(customer))
                                .then(Mono.defer(() -> userModificationService
                                        .modify(customer.getUser(), requestDto.userModificationRequestDto())
                                        .flatMap(user -> {
                                            customer.modify(requestDto.address(), user);
                                            return customerRepository.save(customer);
                                        }))));
    }

}
