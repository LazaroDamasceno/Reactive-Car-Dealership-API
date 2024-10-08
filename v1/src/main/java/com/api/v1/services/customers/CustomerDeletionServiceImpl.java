package com.api.v1.services.customers;

import com.api.v1.domain.customers.CustomersRepository;
import com.api.v1.domain.users.UsersRepository;
import com.api.v1.exceptions.EmptyFluxException;
import com.api.v1.utils.customers.CustomerFinderUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CustomerDeletionServiceImpl implements CustomerDeletionService {

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Mono<Void> deleteBySsn(@NotBlank @Size(min=9, max=9) String ssn) {
        return customerFinderUtil
                .find(ssn)
                .flatMap(customer -> usersRepository
                        .delete(customer.getUser())
                        .then(Mono.defer(() -> customersRepository.delete(customer))
                ));
    }

    @Override
    public Mono<Void> deleteAll() {
        return usersRepository
                .findAll()
                .switchIfEmpty(Mono.error(EmptyFluxException::new))
                .then(Mono.defer(() -> usersRepository
                        .deleteAll()
                        .then(Mono.defer(() -> customersRepository.deleteAll()
                ))));
    }

}
