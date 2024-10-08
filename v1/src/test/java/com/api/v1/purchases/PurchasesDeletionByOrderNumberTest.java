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
public class PurchasesDeletionByOrderNumberTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testSuccessfulDeletion() {
        webTestClient
                .delete()
                .uri("api/v1/purchases/202400001")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void testUnsuccessfulDeletion() {
        webTestClient
                .delete()
                .uri("api/v1/purchases/202400001")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
