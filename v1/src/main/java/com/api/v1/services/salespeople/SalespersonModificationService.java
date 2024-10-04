package com.api.v1.services.salespeople;

import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.dtos.users.UserModificationRequestDto;
import reactor.core.publisher.Mono;

public interface SalespersonModificationService {

    Mono<Salespeople> modify(String employeeId, UserModificationRequestDto requestDto);

}
