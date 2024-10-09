package com.api.v1.controllers.purchases;

import com.api.v1.dtos.purchases.PurchaseResponseDto;
import com.api.v1.services.purchases.PurchaseDeletionService;
import com.api.v1.services.purchases.PurchaseRegistrationService;
import com.api.v1.services.purchases.PurchaseRetrievalService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/purchases")
public class PurchasesController {

    @Autowired
    private PurchaseRegistrationService purchaseRegistrationService;

    @Autowired
    private PurchaseDeletionService purchaseDeletionService;

    @Autowired
    private PurchaseRetrievalService purchaseRetrievalService;

    @PostMapping("{vin}/{ssn}/{employeeId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<PurchaseResponseDto> register(
            @PathVariable @NotBlank @Size(min=13, max=13) String vin,
            @PathVariable @NotBlank @Size(min=9, max=9) String ssn,
            @PathVariable @NotBlank @Size(min=7, max=7) String employeeId
    ) {
        return purchaseRegistrationService.register(vin, ssn, employeeId);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Void> deleteAll() {
        return purchaseDeletionService.deleteAll();
    }

    @DeleteMapping("{orderNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Void> deleteByOrderNumber(@PathVariable @NotBlank @Size(min=9, max=9) String orderNumber) {
        return purchaseDeletionService.deleteByOrderNumber(orderNumber);
    }

    @GetMapping("{employeeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<PurchaseResponseDto> findBySalesperson(@PathVariable @NotBlank @Size(min=7, max=7) String employeeId) {
        return purchaseRetrievalService.findBySalesperson(employeeId);
    }

    @GetMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<PurchaseResponseDto> findByCustomer(@PathVariable @NotBlank @Size(min=9, max=9) String ssn) {
        return purchaseRetrievalService.findByCustomer(ssn);
    }

    @GetMapping("{orderNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<PurchaseResponseDto> findByOrderNumber(
            @PathVariable @NotBlank @Size(min=9, max=9) String orderNumber
    ) {
        return purchaseRetrievalService.findByOrderNumber(orderNumber);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<PurchaseResponseDto> findAll() {
        return purchaseRetrievalService.findAll();
    }

}
