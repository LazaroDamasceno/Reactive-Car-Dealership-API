package com.api.v1.cars;

import com.api.v1.dtos.cars.CarResponseDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarRetrievalByVinTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testSuccessfulRegistration() {
        webTestClient
                .get()
                .uri("api/v1/cars/1234567890123")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(CarResponseDto.class);
    }

    @Test
    void testUnsuccessfulRegistration() {
        webTestClient
                .get()
                .uri("api/v1/cars/1234567890122")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
