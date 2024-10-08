package com.api.v1.services.salespeople;

import com.api.v1.domain.salespeople.SalespeopleRepository;
import com.api.v1.domain.users.UsersRepository;
import com.api.v1.exceptions.EmptyFluxException;
import com.api.v1.exceptions.salespeople.SalespersonNotFoundException;
import com.api.v1.utils.salespeople.SalespersonFinderUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class SalespersonDeletionServiceImpl implements SalespersonDeletionService {

    @Autowired
    private SalespersonFinderUtil salespersonFinderUtil;

    @Autowired
    private SalespeopleRepository salespeopleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Mono<Void> deleteAll() {
        return usersRepository
                .findAll()
                .hasElements()
                .flatMap(hasElements -> {
                    if (!hasElements) return  Mono.error(EmptyFluxException::new);
                    return Mono.defer(() -> usersRepository
                            .deleteAll()
                            .then(Mono.defer(() -> salespeopleRepository.deleteAll()
                    )));
                });
    }

    @Override
    public Mono<Void> deleteByEmployeeId(@NotBlank @Size(min=7, max=7) String employeeId) {
        return salespersonFinderUtil
                .find(employeeId)
                .hasElement()
                .flatMap(exists -> {
                    if (!exists) return Mono.error(new SalespersonNotFoundException(employeeId));
                    return Mono.defer(() -> salespersonFinderUtil
                            .find(employeeId)
                            .flatMap(salesperson -> usersRepository
                                    .delete(salesperson.getUser())
                                    .then(Mono.defer(() -> salespeopleRepository.delete(salesperson)
                    ))));
                });
    }

}
