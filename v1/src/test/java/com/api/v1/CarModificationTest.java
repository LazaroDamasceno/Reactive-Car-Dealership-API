package com.api.v1;

import com.api.v1.dtos.cars.CarModificationRequestDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarModificationTest {

    @Autowired
    WebTestClient webTestClient;

    CarModificationRequestDto requestDto = new CarModificationRequestDto(
            "GM",
            "HUMMER E.V. PICKUP 2XX",
            2023,
            6_730D
    );

    @Test
    @Order(1)
    void testSuccessfulModification() {
        webTestClient
                .put()
                .uri("api/v1/cars/1234567890123")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void testUnsuccessfulModification() {
        webTestClient
                .put()
                .uri("api/v1/cars/1234567890122")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
