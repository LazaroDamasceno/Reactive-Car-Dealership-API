package com.api.v1.services.customers;

import com.api.v1.domain.customers.CustomersRepository;
import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.exceptions.customers.CustomerNotFoundException;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.utils.customers.CustomerResponseMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class CustomerRetrievalServiceImpl implements CustomerRetrievalService {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Override
    public Mono<CustomerResponseDto> findBySsn(@NotBlank @Size(min=9, max=9) String ssn) {
        return customerFinderUtil
                .find(ssn)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(ssn)))
                .flatMap(customer -> Mono.just(CustomerResponseMapper.map(customer)));
    }

    @Override
    public Flux<CustomerResponseDto> findAll() {
        return customersRepository
                .findAll()
                .flatMap(customer -> Flux.just(CustomerResponseMapper.map(customer)));
    }

}
