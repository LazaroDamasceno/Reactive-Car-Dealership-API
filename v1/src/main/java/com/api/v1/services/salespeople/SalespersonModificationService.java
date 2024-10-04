package com.api.v1.services.salespeople;

import com.api.v1.domain.salespeople.Salesperson;
import com.api.v1.dtos.users.UserModificationRequestDto;
import reactor.core.publisher.Mono;

public interface SalespersonModificationService {

    Mono<Salesperson> modify(String employeeId, UserModificationRequestDto requestDto);

}
