package com.api.v1.controllers.purchases;

import com.api.v1.domain.purchases.Purchases;
import com.api.v1.services.purchases.PurchaseRegistrationService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/purchases")
public class PurchasesController {

    @Autowired
    private PurchaseRegistrationService purchaseRegistrationService;

    @PostMapping("{vin}/{ssn}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Purchases> register(
            @PathVariable @NotBlank @Size(min=13, max=13) String vin,
            @PathVariable @NotBlank @Size(min=9, max=9) String ssn
    ) {
        return purchaseRegistrationService.register(vin, ssn);
    }

}
