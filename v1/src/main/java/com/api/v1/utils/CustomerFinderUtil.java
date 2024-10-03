package com.api.v1.utils;

import com.api.v1.domain.customer.Customer;
import com.api.v1.domain.customer.CustomerRepository;
import com.api.v1.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomerFinderUtil {

    @Autowired
    private CustomerRepository customerRepository;

    public Mono<Customer> find(String ssn) {
        return customerRepository
                .findAll()
                .filter(e -> e.getUser().getSsn().equals(ssn))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(ssn)));
    }

}
