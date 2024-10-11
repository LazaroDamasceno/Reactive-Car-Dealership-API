package com.api.v1.services.purchases;

import com.api.v1.domain.cars.CarInventoryRepository;
import com.api.v1.domain.cars.Cars;
import com.api.v1.domain.customers.Customers;
import com.api.v1.domain.purchases.Purchases;
import com.api.v1.domain.purchases.PurchasesRepository;
import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.dtos.purchases.PurchaseResponseDto;
import com.api.v1.exceptions.cars.UnavailableCarException;
import com.api.v1.utils.cars.CarFinderUtil;
import com.api.v1.utils.cars.CarInventoryFinderUtil;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.utils.purchases.PurchaseResponseMapper;
import com.api.v1.utils.salespeople.SalespersonFinderUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.lang.model.element.UnknownAnnotationValueException;

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

    @Autowired
    private CarInventoryRepository carInventoryRepository;

    @Autowired
    private CarInventoryFinderUtil carInventoryFinderUtil;

    @Override
    public Mono<PurchaseResponseDto> register(
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
                    car.setPlateNumber();
                    Customers customer = tuple.getT2();
                    Salespeople salesperson = tuple.getT3();
                    Purchases purchase = Purchases.create(car, customer, salesperson);
                    return carInventoryFinderUtil
                            .find(car)
                            .flatMap(inventory -> {
                                if (inventory.getQuantityAvailable() == 0) {
                                    return Mono.error(UnavailableCarException::new);
                                }
                                inventory.change();
                                return carInventoryRepository
                                        .save(inventory)
                                        .then(Mono.defer(() -> purchaseRepository.save(purchase)));
                            });
                })
                .flatMap(purchase -> Mono.just(PurchaseResponseMapper.map(purchase)));
    }

}
