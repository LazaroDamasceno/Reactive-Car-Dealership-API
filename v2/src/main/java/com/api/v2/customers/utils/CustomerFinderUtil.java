package com.api.v2.customers.utils;

import com.api.v2.customers.CustomerNotFoundException;
import com.api.v2.customers.domain.Customer;
import com.api.v2.customers.domain.CustomerRepository;
import com.api.v2.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerFinderUtil {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public Mono<Customer> find(String ssn) {
        return userRepository
                .findAll()
                .filter(e -> e.getSsn().equals(ssn))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(ssn)))
                .flatMap(user -> customerRepository
                            .findAll()
                            .filter(e -> e.getUser().equals(user))
                            .single()
                );
    }

}
