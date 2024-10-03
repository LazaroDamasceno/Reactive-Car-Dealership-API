package com.api.v1.services;

import com.api.v1.domain.Customer;
import com.api.v1.domain.CustomerRepository;
import com.api.v1.dtos.CustomerModificationRequestDto;
import com.api.v1.utils.CustomerFinderUtil;
import com.api.v1.utils.UserFinderUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CustomerModificationServiceImpl implements CustomerModificationService {

    @Autowired
    private UserFinderUtil userFinderUtil;

    @Autowired
    private UserModificationService userModificationService;

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Customer> modify(
            @NotBlank @Size(min=9, max=9) String ssn,
            @Valid CustomerModificationRequestDto requestDto
    ) {
        return userFinderUtil
                .find(ssn)
                .flatMap(user -> userModificationService.modify(user, requestDto.userModificationRequestDto()))
                .flatMap(modifiedUser -> customerFinderUtil
                        .find(ssn)
                        .flatMap(customer -> {
                            customer.modify(requestDto.address(), modifiedUser);
                            return customerRepository.save(customer);
                }));
    }

}
