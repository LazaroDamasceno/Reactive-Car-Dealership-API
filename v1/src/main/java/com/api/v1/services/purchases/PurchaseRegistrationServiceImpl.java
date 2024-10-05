package com.api.v1.services.purchases;

import com.api.v1.domain.cars.Cars;
import com.api.v1.domain.customers.Customers;
import com.api.v1.domain.purchases.Purchases;
import com.api.v1.domain.purchases.PurchasesRepository;
import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.utils.cars.CarFinderUtil;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.utils.salespeople.SalespersonFinderUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class PurchaseRegistrationServiceImpl implements PurchaseRegistrationService {

    @Autowired
    private CarFinderUtil carFinderUtil;

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Autowired
    private SalespersonFinderUtil salespersonFinderUtil;

    @Autowired
    private PurchasesRepository purchaseRepository;

    @Override
    public Mono<Purchases> register(
            @NotBlank @Size(min=13, max=13) String vin,
            @NotBlank @Size(min=9, max=9) String ssn,
            @NotBlank @Size(min=7, max=7) String employeeId
    ) {
        Mono<Cars> carMono = carFinderUtil.find(vin);
        Mono<Customers> customerMono = customerFinderUtil.find(ssn);
        Mono<Salespeople> salesPersonMono = salespersonFinderUtil.find(employeeId);
        return Mono.zip(carMono, customerMono, salesPersonMono)
                .flatMap(tuple -> {
                    Cars car = tuple.getT1();
                    Customers customer = tuple.getT2();
                    Salespeople salesperson = tuple.getT3();
                    Purchases purchase = new Purchases(car, customer, salesperson);
                    return purchaseRepository.save(purchase);
                });
    }

}
