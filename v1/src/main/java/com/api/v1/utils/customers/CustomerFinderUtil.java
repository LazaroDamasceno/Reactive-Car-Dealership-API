package com.api.v1.utils.customers;

import com.api.v1.domain.customers.Customers;
import com.api.v1.domain.customers.CustomersRepository;
import com.api.v1.exceptions.customers.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomerFinderUtil {

    @Autowired
    private CustomersRepository customerRepository;

    public Mono<Customers> find(String ssn) {
        return customerRepository
                .findAll()
                .filter(e -> e.getUser().getSsn().equals(ssn))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(ssn)));
    }

}
