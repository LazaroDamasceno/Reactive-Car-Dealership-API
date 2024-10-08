package com.api.v1.services.purchases;

import com.api.v1.domain.purchases.PurchasesRepository;
import com.api.v1.exceptions.EmptyFluxException;
import com.api.v1.exceptions.purchases.PurchaseNotFoundException;
import com.api.v1.utils.purchases.PurchaseFinderUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PurchaseDeletionServiceImpl implements PurchaseDeletionService {

    @Autowired
    private PurchaseFinderUtil purchaseFinderUtil;

    @Autowired
    private PurchasesRepository purchasesRepository;

    @Override
    public Mono<Void> deleteAll() {
        return purchasesRepository
                .findAll()
                .switchIfEmpty(Mono.error(EmptyFluxException::new))
                .then(Mono.defer(() -> purchasesRepository.deleteAll()));
    }

    @Override
    public Mono<Void> deleteByOrderNumber(@NotBlank @Size(min=9, max=9) String orderNumber) {
        return purchaseFinderUtil
                .find(orderNumber)
                .switchIfEmpty(Mono.error(new PurchaseNotFoundException(orderNumber)))
                .flatMap(purchase -> purchasesRepository.delete(purchase));
    }

}
