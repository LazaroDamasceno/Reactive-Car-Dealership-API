package com.api.v1;

import com.api.v1.dtos.user.UserModificationRequestDto;
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
class SalespersonModificationTest {

    @Autowired
    WebTestClient webTestClient;

    UserModificationRequestDto requestDto = new UserModificationRequestDto(
            "Wilson",
            "",
            "Softener",
            LocalDate.parse("2000-12-12"),
            "Softener@w.Softener.com",
            "male",
            "1234567890"
    );

    @Test
    @Order(1)
    void testSuccessfulRegistration() {
        webTestClient
                .put()
                .uri("api/v1/salespeople/1000011")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void testUnsuccessfulRegistration() {
        webTestClient
                .put()
                .uri("api/v1/salespeople/1000030")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
