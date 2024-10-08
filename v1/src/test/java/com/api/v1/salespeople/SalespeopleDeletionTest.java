package com.api.v1.salespeople;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SalespeopleDeletionTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testSuccessfulDeletion() {
        webTestClient
                .delete()
                .uri("api/v1/salespeople")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void testUnsuccessfulDeletion() {
        webTestClient
                .delete()
                .uri("api/v1/salespeople")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
