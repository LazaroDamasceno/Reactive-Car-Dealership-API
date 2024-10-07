package com.api.v1.services.salespeople;

import reactor.core.publisher.Mono;

public interface SalespersonDeletionService {

    Mono<Void> deleteAll();
    Mono<Void> deleteByEmployeeId(String employeeId);

}
