package com.api.v1.services.customers;

import reactor.core.publisher.Mono;

public interface CustomerDeletionService {

    Mono<Void> deleteBySsn(String ssn);
    Mono<Void> deleteAll();

}
