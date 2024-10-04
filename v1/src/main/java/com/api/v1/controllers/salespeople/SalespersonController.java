package com.api.v1.controllers.salespeople;

import com.api.v1.domain.salespeople.Salesperson;
import com.api.v1.dtos.users.UserModificationRequestDto;
import com.api.v1.dtos.users.UserRegistrationRequestDto;
import com.api.v1.services.salespeople.SalespersonModificationService;
import com.api.v1.services.salespeople.SalespersonRegistrationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/salespeople")
public class SalespersonController {

    @Autowired
    private SalespersonRegistrationService salespersonRegistrationService;

    @Autowired
    private SalespersonModificationService salespersonModificationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Salesperson> register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return salespersonRegistrationService.register(requestDto);
    }

    @PutMapping("{employeeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Salesperson> modify(
            @NotBlank @Size(min=7, max=7) @PathVariable String employeeId,
            @RequestBody UserModificationRequestDto requestDto
    ) {
        return salespersonModificationService.modify(employeeId, requestDto);
    }
}
