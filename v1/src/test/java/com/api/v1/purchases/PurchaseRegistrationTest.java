package com.api.v1.purchases;

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
        int testcases = 25;
        while (testcases > 0) {
            webTestClient
                    .post()
                    .uri("api/v1/purchases/1234567890123/123456789/1000076")
                    .exchange()
                    .expectStatus()
                    .is2xxSuccessful();
            testcases--;
        }

    }

    @Test
    void testUnsuccessfulRegistration1() {
        webTestClient
                .post()
                .uri("api/v1/purchases/1234567890122/123456789/1000076")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void testUnsuccessfulRegistration2() {
        webTestClient
                .post()
                .uri("api/v1/purchases/1234567890123/123456788/1000076")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void testUnsuccessfulRegistration3() {
        webTestClient
                .post()
                .uri("api/v1/purchases/1234567890123/123456789/1000077")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void testUnsuccessfulRegistration4() {
        webTestClient
                .post()
                .uri("api/v1/purchases/1234567890122/123456788/1000077")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
