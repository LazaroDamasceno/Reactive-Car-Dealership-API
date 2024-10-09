package com.api.v1.services.purchases;

import com.api.v1.domain.purchases.PurchasesRepository;
import com.api.v1.dtos.purchases.PurchaseResponseDto;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.utils.purchases.PurchaseFinderUtil;
import com.api.v1.utils.purchases.PurchaseResponseMapper;
import com.api.v1.utils.salespeople.SalespersonFinderUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class PurchaseRetrievalServiceImpl implements PurchaseRetrievalService {

    @Autowired
    private PurchasesRepository purchasesRepository;

    @Autowired
    private PurchaseFinderUtil purchaseFinderUtil;

    @Autowired
    private SalespersonFinderUtil salespersonFinderUtil;

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Override
    public Flux<PurchaseResponseDto> findBySalesperson(@NotBlank @Size(min=7, max=7) String employeeId) {
        return salespersonFinderUtil
                .find(employeeId)
                .flatMapMany(salesperson -> purchasesRepository
                        .findAll()
                        .filter(e -> e.salesperson().equals(salesperson))
                        .flatMap(sp -> Flux.just(PurchaseResponseMapper.map(sp)))
                );
    }

    @Override
    public Flux<PurchaseResponseDto> findByCustomer(@NotBlank @Size(min=9, max=9) String ssn) {
        return customerFinderUtil
                .find(ssn)
                .flatMapMany(customer -> purchasesRepository
                        .findAll()
                        .filter(e -> e.customers().equals(customer))
                        .flatMap(sp -> Flux.just(PurchaseResponseMapper.map(sp)))
                );
    }

    @Override
    public Mono<PurchaseResponseDto> findByOrderNumber(@NotBlank @Size(min=9, max=9) String orderNumber) {
        return purchaseFinderUtil
                .find(orderNumber)
                .flatMap(purchase -> Mono.just(PurchaseResponseMapper.map(purchase)));
    }

    @Override
    public Flux<PurchaseResponseDto> findAll() {
        return purchasesRepository
                .findAll()
                .flatMap(purchase -> Flux.just(PurchaseResponseMapper.map(purchase)));
    }

}
