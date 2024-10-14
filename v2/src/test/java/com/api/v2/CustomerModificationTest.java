package com.api.v2;

import com.api.v2.customers.dtos.CustomerResponseDto;
import com.api.v2.users.dtos.UserModificationRequestDto;
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
class CustomerModificationTest {

	@Autowired
	private WebTestClient webTestClient;

	UserModificationRequestDto userModificationRequestDto = new UserModificationRequestDto(
			"Leo",
			"Silva",
			"Santos Jr.",
			LocalDate.parse("1997-12-12"),
			"jr@leosantos.com",
			"cis male",
			"0987654321"
	);

	@Test
	@Order(1)
	void testSuccessfulModification() {
		webTestClient
				.put()
				.uri("api/v2/customers/123456789")
				.bodyValue(userModificationRequestDto)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(CustomerResponseDto.class);
	}

	@Test
	void testUnsuccessfulModification() {
		webTestClient
				.put()
				.uri("api/v2/customers/123456788")
				.bodyValue(userModificationRequestDto)
				.exchange()
				.expectStatus().is5xxServerError();
	}

}
