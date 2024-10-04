package com.api.v1.controllers.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.dtos.cars.CarModificationRequestDto;
import com.api.v1.dtos.cars.CarRegistrationRequestDto;
import com.api.v1.services.cars.CarModificationService;
import com.api.v1.services.cars.CarRegistrationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/cars")
public class CarsController {

    @Autowired
    private CarRegistrationService carRegistrationService;

    @Autowired
    private CarModificationService carModificationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Cars> register(@Valid @RequestBody CarRegistrationRequestDto requestDto) {
        return carRegistrationService.register(requestDto);
    }

    @PutMapping("{vin}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Cars> modify(
            @PathVariable @NotBlank @Size(min=13, max=13) String vin,
            @RequestBody @Valid CarModificationRequestDto requestDto
    ) {
        return carModificationService.modify(vin, requestDto);
    }
}
