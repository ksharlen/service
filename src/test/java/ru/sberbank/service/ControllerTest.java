package ru.sberbank.service;

import org.junit.*;
import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.service.domain.User;
//import org.springframework.util.Assert;
import ru.sberbank.service.domain.UserWrapper;
import ru.sberbank.service.exception.ConflictException;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {
	@LocalServerPort
	int randomServerPort;

//	@Test
//	public void testGetEmployeeListSuccess() throws URISyntaxException
//	{
//		RestTemplate restTemplate = new RestTemplate();
//
//		final String baseUrl = "http://localhost:" + randomServerPort + "/registration/create";
//		final String uUsers = "http://localhost:" + randomServerPort + "/registration/getusers";
//
////		HttpEntity<User> he = new HttpEntity<>(new User("alex", "123"));
//
////		ResponseEntity<User> result = restTemplate.postForEntity(new URI(baseUrl), he, User.class);
//
//		ResponseEntity<UserWrapper> re = restTemplate.postForEntity(new URI(uUsers), null,  UserWrapper.class);
//
//		//Verify request succeed
////		Assert.isTrue(HttpStatus.OK == result.getStatusCode(), "http state not OK");
//		Assert.isTrue(HttpStatus.OK == re.getStatusCode(), "HttpStatus not OK");
//		Assert.isNull(re.getBody(), "is null");
//		Assert.isTrue(re.getBody().getUsers().isEmpty(), "is null");
//	}

	@Test
	public void testCreateUser() {
		RestTemplate restTemplate = new RestTemplate();
		final String url = "http://localhost:" + randomServerPort + "/registration/create";
		User userTest = new User("Alexandr", "Akinin", "Sergeevich", "alex", "123");

		HttpEntity<User> he = new HttpEntity<>(userTest);
		ResponseEntity<User> result = restTemplate.postForEntity(url, he, User.class);
		Assert.assertSame(HttpStatus.OK, result.getStatusCode());
		Assert.assertNotNull(result.getBody());
		Assert.assertEquals(userTest, result.getBody());
	}


}