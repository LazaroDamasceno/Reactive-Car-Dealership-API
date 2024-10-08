package com.api.v1.controllers.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.dtos.cars.CarModificationRequestDto;
import com.api.v1.dtos.cars.CarRegistrationRequestDto;
import com.api.v1.dtos.cars.CarResponseDto;
import com.api.v1.services.cars.CarModificationService;
import com.api.v1.services.cars.CarRegistrationService;
import com.api.v1.services.cars.CarRetrievalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/cars")
public class CarsController {

    @Autowired
    private CarRegistrationService carRegistrationService;

    @Autowired
    private CarModificationService carModificationService;

    @Autowired
    private CarRetrievalService carRetrievalService;

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

    @GetMapping("{vin}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<CarResponseDto> findByVin(@NotBlank @Size(min=13, max=13) @PathVariable String vin) {
        return carRetrievalService.findByVin(vin);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CarResponseDto> findAll() {
        return carRetrievalService.findAll();
    }

}
