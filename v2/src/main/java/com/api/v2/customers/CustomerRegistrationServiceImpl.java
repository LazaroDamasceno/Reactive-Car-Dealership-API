package com.api.v2.customers;

import com.api.v2.users.services.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private final UserRegistrationService userRegistrationService;
    private final CustomerRepository customerRepository;

    @Override
    public Mono<CustomerResponseDto> register(@Valid CustomerRegistrationRequestDto requestDto) {
        return userRegistrationService
                .register(requestDto.userRegistrationRequestDto())
                .flatMap(user -> Mono.defer(() -> {
                    Customer customer = Customer.of(requestDto.address(), user);
                    return customerRepository.save(customer);
                }))
                .flatMap(customer -> Mono.just(CustomerRequestMapper.map(customer)));
    }

}
