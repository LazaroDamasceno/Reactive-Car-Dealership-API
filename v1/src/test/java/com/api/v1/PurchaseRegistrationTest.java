package com.api.v1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PurchaseRegistrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testSuccessfulRegistration() {
        webTestClient
                .post()
                .uri("api/v1/purchases/%s/%s/%s".formatted(1234567890123L, 123456789L, 10000011))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void testUnsuccessfulRegistration1() {
        webTestClient
                .post()
                .uri("api/v1/purchases/%s/%s/%s".formatted(1234567890123L, 123456789L, 10000011))
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void testUnsuccessfulRegistration2() {
        webTestClient
                .post()
                .uri("api/v1/purchases/%s/%s/%s".formatted(1234567890122L, 123456788L, 10000011))
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void testUnsuccessfulRegistration3() {
        webTestClient
                .post()
                .uri("api/v1/purchases/%s/%s/%s".formatted(1234567890123L, 123456789L, 10000011))
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
