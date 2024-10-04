package com.api.v1.services.salespeople;

import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface SalespersonRegistrationService {

    Mono<Salespeople> register(UserRegistrationRequestDto requestDto);

}
