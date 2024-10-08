package com.api.v1.services.salespeople;

import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.domain.salespeople.SalespeopleRepository;
import com.api.v1.dtos.salespeople.SalespersonResponseDto;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import com.api.v1.services.users.UserRegistrationService;
import com.api.v1.utils.salespeople.SalespersonResponseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class SalespersonRegistrationServiceImpl implements SalespersonRegistrationService {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private SalespeopleRepository salespersonRepository;

    @Override
    public Mono<SalespersonResponseDto> register(@Valid UserRegistrationRequestDto requestDto) {
        return userRegistrationService
                .register(requestDto)
                .flatMap(user -> {
                    Salespeople salesperson = Salespeople.create(user);
                    return salespersonRepository.save(salesperson);
                })
                .flatMap(e -> Mono.just(SalespersonResponseMapper.map(e)));
    }

}
