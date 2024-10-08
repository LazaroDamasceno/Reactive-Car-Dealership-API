package com.api.v1.services.salespeople;

import com.api.v1.dtos.salespeople.SalespersonResponseDto;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import reactor.core.publisher.Mono;

public interface SalespersonRegistrationService {

    Mono<SalespersonResponseDto> register(UserRegistrationRequestDto requestDto);

}
