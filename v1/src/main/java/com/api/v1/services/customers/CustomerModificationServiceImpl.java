package com.api.v1.services.customers;

import com.api.v1.domain.changes_records.CustomerChangesRecord;
import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.changes_records.CustomerChangesRecordRepository;
import com.api.v1.domain.customers.CustomerRepository;
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
    private CustomerChangesRecordRepository auditTrailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Customer> modify(
            @NotBlank @Size(min = 9, max = 9) String ssn,
            @Valid CustomerModificationRequestDto requestDto
    ) {
        return customerFinderUtil
                .find(ssn)
                .flatMap(customer -> auditTrailRepository.save(new CustomerChangesRecord(customer))
                                .then(Mono.defer(() -> userModificationService
                                        .modify(customer.getUser(), requestDto.userModificationRequestDto())
                                        .flatMap(user -> {
                                            customer.modify(requestDto.address(), user);
                                            return customerRepository.save(customer);
                                        }))));
    }

}
