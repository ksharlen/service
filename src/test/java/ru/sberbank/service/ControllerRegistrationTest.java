package ru.sberbank.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.repos.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControllerRegistrationTest {
	@LocalServerPort
	private int randomServerPort;

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TestRestTemplate restTemplate;

	private final UserDto expectedUser = new UserDto("Alex", "Akinin", "miily", "123");

	@Before
	public void init() {
		userRepo.deleteAll();
	}

	@Test
	public void registrationUser() {
		final String url = "http://localhost:" + randomServerPort + "/registration";
		RegistrationDto newUser = new RegistrationDto("Alex", "Akinin", "miily", "123");
		HttpEntity<RegistrationDto> he = new HttpEntity<>(newUser);
		ResponseEntity<UserDto> re = restTemplate.postForEntity(url, he, UserDto.class);

		Assert.assertNotNull(re.getBody());
		Assert.assertEquals(200, re.getStatusCode().value());
		Assert.assertEquals(expectedUser, re.getBody());
	}
}
