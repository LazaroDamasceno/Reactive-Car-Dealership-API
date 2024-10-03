package com.api.v1.services;

import com.api.v1.domain.Customer;
import com.api.v1.domain.CustomerRepository;
import com.api.v1.dtos.CustomerRegistrationRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

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
                .flatMap(newUser -> {
                   Customer newCustomer = Customer
                           .builder()
                           .id(UUID.randomUUID())
                           .address(requestDto.address())
                           .user(newUser)
                           .createdAt(Instant.now())
                           .createdAtZone(ZoneId.systemDefault())
                           .build();
                   return customerRepository.save(newCustomer);
                });
    }

}
