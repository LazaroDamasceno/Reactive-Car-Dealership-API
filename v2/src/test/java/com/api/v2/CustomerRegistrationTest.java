package com.api.v2;

import com.api.v2.customers.dtos.CustomerRegistrationRequestDto;
import com.api.v2.customers.dtos.CustomerResponseDto;
import com.api.v2.users.dtos.UserRegistrationRequestDto;
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
class CustomerRegistrationTest {

	@Autowired
	private WebTestClient webTestClient;

	UserRegistrationRequestDto userRegistrationRequestDto = new UserRegistrationRequestDto(
			"Leo",
			"",
			"Santos",
			LocalDate.parse("2000-12-12"),
			"123456789",
			"leosantos@mail.com",
			"male",
			"1234567890"
	);

	CustomerRegistrationRequestDto customerRegistrationRequestDto = new CustomerRegistrationRequestDto(
			"St. Dennis, Paris",
			userRegistrationRequestDto
	);

	@Test
	@Order(1)
	void testSuccessfulRegistration() {
		webTestClient
				.post()
				.uri("api/v2/customers")
				.bodyValue(customerRegistrationRequestDto)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(CustomerResponseDto.class);
	}

	@Test
	void testUnsuccessfulRegistration() {
		webTestClient
				.post()
				.uri("api/v2/customers")
				.bodyValue(customerRegistrationRequestDto)
				.exchange()
				.expectStatus().is5xxServerError();
	}

}
