package com.api.v1.services.cars;

import com.api.v1.domain.cars.CarsRepository;
import com.api.v1.exceptions.EmptyFluxException;
import com.api.v1.exceptions.cars.CarNotFoundException;
import com.api.v1.utils.cars.CarFinderUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CarDeletionServiceImpl implements CarDeletionService {

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private CarFinderUtil carFinderUtil;

    @Override
    public Mono<Void> deleteByVin(@NotBlank @Size(min=13, max=13) String vin) {
        return carFinderUtil
                .find(vin)
                .switchIfEmpty(Mono.error(new CarNotFoundException(vin)))
                .flatMap(car ->carsRepository.delete(car));
    }

    @Override
    public Mono<Void> deleteAll() {
        return carsRepository
                .findAll()
                .switchIfEmpty(Mono.error(EmptyFluxException::new))
                .then(Mono.defer(() -> carsRepository.deleteAll()));
    }

}
