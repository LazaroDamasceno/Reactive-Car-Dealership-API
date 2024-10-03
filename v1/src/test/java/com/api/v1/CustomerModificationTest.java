package com.api.v1;

import com.api.v1.dtos.CustomerModificationRequestDto;
import com.api.v1.dtos.UserModificationRequestDto;
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
class CustomerModificationTest {

    @Autowired
    WebTestClient webTestClient;

    CustomerModificationRequestDto requestDto = new CustomerModificationRequestDto(
            "St. Dennis, Paris, EU",
            new UserModificationRequestDto(
                    "Leo",
                    "Silva",
                    "Santos Jr",
                    LocalDate.parse("2000-12-12"),
                    "jr@leosantos.io",
                    "cis male",
                    "0987654321"
            )
    );

    @Test
    @Order(1)
    void testSuccessfulModification() {
        webTestClient
                .put()
                .uri("api/v1/customers/123456789")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void testUnsuccessfulModification() {
        webTestClient
                .put()
                .uri("api/v1/customers/123456788")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
