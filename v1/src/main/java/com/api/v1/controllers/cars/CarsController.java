package com.api.v1.controllers.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.dtos.cars.CarRegistrationRequestDto;
import com.api.v1.services.cars.CarRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/cars")
public class CarsController {

    @Autowired
    private CarRegistrationService carRegistrationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Cars> register(@Valid CarRegistrationRequestDto requestDto) {
        return carRegistrationService.register(requestDto);
    }

}
