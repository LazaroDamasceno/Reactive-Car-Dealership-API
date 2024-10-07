package com.api.v1.services.salespeople;

import com.api.v1.domain.salespeople.SalespeopleRepository;
import com.api.v1.dtos.salespeople.SalespersonResponseDto;
import com.api.v1.exceptions.EmptyFluxException;
import com.api.v1.exceptions.salespeople.SalespersonNotFoundException;
import com.api.v1.utils.salespeople.SalespersonFinderUtil;
import com.api.v1.utils.salespeople.SalespersonResponseMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class SalespersonRetrievalServiceImpl implements SalespersonRetrievalService {

    @Autowired
    private SalespeopleRepository salespeopleRepository;

    @Autowired
    private SalespersonFinderUtil salespersonFinderUtil;

    @Override
    public Flux<SalespersonResponseDto> findAll() {
        return salespeopleRepository
                .findAll()
                .switchIfEmpty(Mono.error(EmptyFluxException::new))
                .flatMap(e -> Flux.just(SalespersonResponseMapper.map(e)));
    }

    @Override
    public Mono<SalespersonResponseDto> findBySsn(@NotBlank @Size(min=7, max=7) String employeeId) {
        return salespersonFinderUtil
                .find(employeeId)
                .switchIfEmpty(Mono.error(new SalespersonNotFoundException(employeeId)))
                .flatMap(e -> Mono.just(SalespersonResponseMapper.map(e)));
    }

}
