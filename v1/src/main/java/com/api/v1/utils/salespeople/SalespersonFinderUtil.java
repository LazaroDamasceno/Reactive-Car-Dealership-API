package com.api.v1.utils.salespeople;

import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.domain.salespeople.SalespeopleRepository;
import com.api.v1.exceptions.salespeople.SalespersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Component
public class SalespersonFinderUtil {

    @Autowired
    private SalespeopleRepository salespersonRepository;

    public Mono<Salespeople> find(String employeeId) {
        return salespersonRepository
                .findAll()
                .filter(e -> e.getEmployeeId().equals(new BigInteger(employeeId)))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new SalespersonNotFoundException(employeeId)));
    }

}
