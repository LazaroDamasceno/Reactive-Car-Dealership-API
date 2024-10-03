package com.api.v1.services.salesperson;

import com.api.v1.domain.salesperson.Salesperson;
import com.api.v1.domain.salesperson.SalespersonRepository;
import com.api.v1.dtos.UserRegistrationRequestDto;
import com.api.v1.services.user.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class SalespersonRegistrationServiceImpl implements SalespersonRegistrationService {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private SalespersonRepository salespersonRepository;

    @Override
    public Mono<Salesperson> register(@Valid UserRegistrationRequestDto requestDto) {
        return userRegistrationService
                .register(requestDto)
                .flatMap(user -> {
                    Salesperson salesperson = new Salesperson(user);
                    return salespersonRepository.save(salesperson);
                });
    }

}
