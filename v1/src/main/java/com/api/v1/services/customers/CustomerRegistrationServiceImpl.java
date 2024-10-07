package com.api.v1.services.customers;

import com.api.v1.domain.customers.Customers;
import com.api.v1.domain.customers.CustomersRepository;
import com.api.v1.dtos.customers.CustomerRegistrationRequestDto;
import com.api.v1.services.users.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private CustomersRepository customerRepository;

    @Override
    public Mono<Customers> register(@Valid CustomerRegistrationRequestDto requestDto) {
        return userRegistrationService
                .register(requestDto.userRegistrationRequestDto())
                .flatMap(modifiedUser -> {
                   Customers newCustomer = Customers.create(requestDto.address(), modifiedUser);
                   return customerRepository.save(newCustomer);
                });
    }

}
