package com.api.v1;

import com.api.v1.domain.User;
import com.api.v1.dtos.UserRegistrationRequestDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRegistrationTest {

	@Autowired
	private WebTestClient webTestClient;

	UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto(
			"Leo",
			"",
			"Santos",
			"123456789",
			LocalDate.parse("2000-12-12"),
			"leosantos@gmail.com",
			"male",
			"1234567890"
	);

	@Test
	@Order(1)
	void testSuccessfulRegistration() {
		webTestClient
				.post()
				.uri("api/v1/users")
				.bodyValue(requestDto)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody(User.class);
	}

	@Test
	void testUnsuccessfulRegistration() {
		webTestClient
				.post()
				.uri("api/v1/users")
				.bodyValue(requestDto)
				.exchange()
				.expectStatus()
				.is5xxServerError();
	}

}
