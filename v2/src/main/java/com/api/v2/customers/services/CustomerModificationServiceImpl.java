package com.api.v2.customers.services;

import com.api.v2.customers.domain.CustomerRepository;
import com.api.v2.customers.dtos.CustomerModificationRequestDto;
import com.api.v2.customers.dtos.CustomerResponseDto;
import com.api.v2.customers.utils.CustomerFinderUtil;
import com.api.v2.customers.utils.CustomerResponseMapper;
import com.api.v2.users.services.UserModificationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
class CustomerModificationServiceImpl implements CustomerModificationService {

    private final UserModificationService userModificationService;
    private final CustomerRepository customerRepository;

    @Override
    public Mono<CustomerResponseDto> modify(
            @NotBlank @Size(min=9, max=9) String ssn,
            @Valid CustomerModificationRequestDto requestDto
    ) {
        return CustomerFinderUtil
                .find(ssn)
                .flatMap(customer -> userModificationService
                        .modify(customer.getUser(), requestDto.userModificationRequestDto())
                        .flatMap(user -> {
                            customer.modify(requestDto.address(), user);
                            return customerRepository.save(customer);
                        })
                )
                .flatMap(customer -> Mono.just(CustomerResponseMapper.map(customer)));
    }

}
