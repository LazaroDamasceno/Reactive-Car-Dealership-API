package com.api.v1.purchases;

import com.api.v1.dtos.purchases.PurchaseResponseDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchaseRetrievalTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testSuccessfulRetrieval1() {
        webTestClient
                .get()
                .uri("api/v1/purchases/employeeId/1000033")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(PurchaseResponseDto.class);
    }

    @Test
    @Order(2)
    void testSuccessfulRetrieval2() {
        webTestClient
                .get()
                .uri("api/v1/purchases/ssn/123456789")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(PurchaseResponseDto.class);
    }

    @Test
    @Order(3)
    void testSuccessfulRetrieval3() {
        webTestClient
                .get()
                .uri("api/v1/purchases/order-number/202400001")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(PurchaseResponseDto.class);
    }

    @Test
    @Order(4)
    void testSuccessfulRetrieval4() {
        webTestClient
                .get()
                .uri("api/v1/purchases")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(PurchaseResponseDto.class);
    }

    @Test
    void testUnsuccessfulRetrieval1() {
        webTestClient
                .get()
                .uri("api/v1/purchases/employeeId/1000030")
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testUnsuccessfulRetrieval2() {
        webTestClient
                .get()
                .uri("api/v1/purchases/ssn/123456788")
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testUnsuccessfulRetrieval3() {
        webTestClient
                .get()
                .uri("api/v1/purchases/order-number/202400000")
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
