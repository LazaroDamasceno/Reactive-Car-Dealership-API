package com.api.v1.services.salesperson;

import com.api.v1.domain.salesperson.Salesperson;
import com.api.v1.dtos.user.UserRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface SalespersonRegistrationService {

    Mono<Salesperson> register(UserRegistrationRequestDto requestDto);

}
