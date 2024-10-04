package com.api.v1.services.salesperson;

import com.api.v1.domain.salesperson.Salesperson;
import com.api.v1.dtos.user.UserModificationRequestDto;
import reactor.core.publisher.Mono;

public interface SalespersonModificationService {

    Mono<Salesperson> modify(String employeeId, UserModificationRequestDto requestDto);

}
