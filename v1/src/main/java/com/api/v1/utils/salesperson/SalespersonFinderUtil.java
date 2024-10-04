package com.api.v1.utils.salesperson;

import com.api.v1.domain.salesperson.Salesperson;
import com.api.v1.domain.salesperson.SalespersonRepository;
import com.api.v1.exceptions.salesperson.SalespersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Component
public class SalespersonFinderUtil {

    @Autowired
    private SalespersonRepository salespersonRepository;

    public Mono<Salesperson> find(String employeeId) {
        return salespersonRepository
                .findAll()
                .filter(e -> e.getEmployeeId().equals(new BigInteger(employeeId)))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new SalespersonNotFoundException(employeeId)));
    }

}
