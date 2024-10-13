package com.api.v2.customers;

import com.api.v2.users.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, UUID> {

    @Query("""
        { "user": ?0 }
    """)
    Mono<Customer> findByUser(User user);

}
