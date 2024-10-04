package com.api.v1.services.customer;

import com.api.v1.domain.customer.Customer;
import com.api.v1.domain.customer.CustomerRepository;
import com.api.v1.dtos.customer.CustomerRegistrationRequestDto;
import com.api.v1.services.user.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Customer> register(@Valid CustomerRegistrationRequestDto requestDto) {
        return userRegistrationService
                .register(requestDto.userRegistrationRequestDto())
                .flatMap(modifiedUser -> {
                   Customer newCustomer = new Customer(requestDto.address(), modifiedUser);
                   return customerRepository.save(newCustomer);
                });
    }

}
