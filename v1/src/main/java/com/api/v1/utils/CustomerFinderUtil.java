package com.api.v1.utils;

import com.api.v1.domain.Customer;
import com.api.v1.domain.CustomerRepository;
import com.api.v1.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Component
public class CustomerFinderUtil {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserFinderUtil userFinderUtil;

    public Mono<Customer> find(String ssn) {
        return userFinderUtil
                .find(ssn)
                .flatMap(user -> customerRepository
                        .findAll()
                        .filter(e -> e.getUser().equals(user))
                        .single()
                        .onErrorResume(ex -> Mono.error(new CustomerNotFoundException(ssn))));
            );
    }

}
