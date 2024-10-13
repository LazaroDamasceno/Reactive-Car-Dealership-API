package com.api.v2.users.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Query;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveMongoRepository<User, UUID> {

    @Query("""
        { "ssn": ?0 }
    """)
    Mono<User> findBySsn(String ssn);
}
