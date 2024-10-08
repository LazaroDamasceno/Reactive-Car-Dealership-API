package com.api.v1.services.salespeople;

import com.api.v1.dtos.salespeople.SalespersonResponseDto;
import com.api.v1.dtos.users.UserModificationRequestDto;
import reactor.core.publisher.Mono;

public interface SalespersonModificationService {

    Mono<SalespersonResponseDto> modify(String employeeId, UserModificationRequestDto requestDto);

}
