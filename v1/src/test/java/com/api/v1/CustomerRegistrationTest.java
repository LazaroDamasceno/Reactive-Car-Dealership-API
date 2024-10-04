package com.api.v1;

import com.api.v1.dtos.customer.CustomerRegistrationRequestDto;
import com.api.v1.dtos.user.UserRegistrationRequestDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerRegistrationTest {

    @Autowired
    WebTestClient webTestClient;

    CustomerRegistrationRequestDto requestDto = new CustomerRegistrationRequestDto(
            "St. Dennis, Paris",
            new UserRegistrationRequestDto(
                    "Leo",
                    "",
                    "Santos",
                    "123456789",
                    LocalDate.parse("2000-12-12"),
                    "leosantos@mail.com",
                    "male",
                    "1234567890"
            )
    );

    @Test
    @Order(1)
    void testSuccessfulRegistration() {
        webTestClient
                .post()
                .uri("api/v1/customers")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void testUnsuccessfulRegistration() {
        webTestClient
                .post()
                .uri("api/v1/customers")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
