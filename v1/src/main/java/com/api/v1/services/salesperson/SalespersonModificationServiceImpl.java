package com.api.v1.services.salesperson;

import com.api.v1.domain.salesperson.Salesperson;
import com.api.v1.domain.salesperson.SalespersonRepository;
import com.api.v1.domain.user.User;
import com.api.v1.domain.user.UserRepository;
import com.api.v1.dtos.UserModificationRequestDto;
import com.api.v1.services.user.UserModificationService;
import com.api.v1.utils.SalespersonFinderUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class SalespersonModificationServiceImpl implements SalespersonModificationService {

    @Autowired
    private SalespersonFinderUtil salespersonFinderUtil;

    @Autowired
    private UserModificationService userModificationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalespersonRepository salespersonRepository;

    @Override
    public Mono<Salesperson> modify(
            @NotBlank @Size(min=7, max=7) String employeeId,
            @Valid UserModificationRequestDto requestDto
    ) {
        return salespersonFinderUtil
                .find(employeeId)
                .flatMap(salesperson -> userModificationService.modify(salesperson.getUser(), requestDto))
                .then(Mono.defer(() -> salespersonFinderUtil
                            .find(employeeId)
                            .flatMap(salesperson -> {
                                User user = salesperson.getUser();
                                user.modify(requestDto);
                                return userRepository.save(user)
                                        .flatMap(modifiedUser -> {
                                            salesperson.modify(user);
                                            return salespersonRepository.save(salesperson);
                                        });
                            })));
    }

}
