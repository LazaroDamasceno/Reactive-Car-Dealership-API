package com.api.v1.controllers.salespeople;

import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.dtos.salespeople.SalespersonResponseDto;
import com.api.v1.dtos.users.UserModificationRequestDto;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import com.api.v1.services.salespeople.SalespersonDeletionService;
import com.api.v1.services.salespeople.SalespersonModificationService;
import com.api.v1.services.salespeople.SalespersonRegistrationService;
import com.api.v1.services.salespeople.SalespersonRetrievalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/salespeople")
public class SalespeopleController {

    @Autowired
    private SalespersonRegistrationService salespersonRegistrationService;

    @Autowired
    private SalespersonModificationService salespersonModificationService;

    @Autowired
    private SalespersonRetrievalService salespersonRetrievalService;

    @Autowired
    private SalespersonDeletionService salespersonDeletionService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Salespeople> register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return salespersonRegistrationService.register(requestDto);
    }

    @PutMapping("{employeeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Salespeople> modify(
            @NotBlank @Size(min=7, max=7) @PathVariable String employeeId,
            @RequestBody UserModificationRequestDto requestDto
    ) {
        return salespersonModificationService.modify(employeeId, requestDto);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAll() {
        return salespersonDeletionService.deleteAll();
    }

    @DeleteMapping("{employeeId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBySsn(@PathVariable @NotBlank @Size(min=7, max=7) String employeeId) {
        return salespersonDeletionService.deleteByEmployeeId(employeeId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<SalespersonResponseDto> findAll() {
        return salespersonRetrievalService.findAll();
    }

    @GetMapping("{employeeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<SalespersonResponseDto> findBySsn(@PathVariable @NotBlank @Size(min=7, max=7) String employeeId) {
        return salespersonRetrievalService.findByEmployeeId(employeeId);
    }

}
