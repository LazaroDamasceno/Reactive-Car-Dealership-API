package com.api.v1.customers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerRetrievalBySsnTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testSuccessfulRetrieval() {
        webTestClient
                .get()
                .uri("api/v1/customers/123456789")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void testUnsuccessfulRetrieval() {
        webTestClient
                .get()
                .uri("api/v1/customers/123456788")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
