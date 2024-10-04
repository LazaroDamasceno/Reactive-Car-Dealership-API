package com.api.v1.services.customer;

import com.api.v1.domain.audit_trail.CustomerChangesRecord;
import com.api.v1.domain.customer.Customer;
import com.api.v1.domain.audit_trail.CustomerChangesRecordRepository;
import com.api.v1.domain.customer.CustomerRepository;
import com.api.v1.dtos.customer.CustomerModificationRequestDto;
import com.api.v1.services.user.UserModificationService;
import com.api.v1.utils.customer.CustomerFinderUtil;
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
