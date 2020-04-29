package ru.sberbank.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.service.dto.CardDto;
import ru.sberbank.service.dto.NewCardDto;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.repos.CardRepo;
import ru.sberbank.service.repos.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerCardTest {
	@LocalServerPort
	private int randomServerPort;
	@Autowired
	private CardRepo cardRepo;
	@Autowired
	private UserRepo userRepo;

	@Before
	public void initTest() {
		cardRepo.deleteAll();
	}

	@Test
	public void addNewCardTest() {
	final RestTemplate restTemplate = new RestTemplate();
		String testLoginUser = "miily";
		final String baseUrl = "http://localhost:" + randomServerPort + "/" + testLoginUser + "/cards/";
		NewCardDto newCardDto = new NewCardDto("Alexandr", "Akinin");

		createTestEntities();
		HttpEntity<NewCardDto> request = new HttpEntity<>(newCardDto);
		ResponseEntity<CardDto> response = restTemplate.postForEntity(baseUrl, request, CardDto.class);
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals("Alexandr", response.getBody().getName());
		Assert.assertEquals("Akinin", response.getBody().getLastName());
		Assert.assertEquals(0, (long) response.getBody().getBalance());
	}

	private void createTestEntities() {
		User user1 = new User("Alexandr", "Akinin", "miily", "123");
		User user2 = new User("Sergey", "Volkov", "kros", "41");
		User user3 = new User("Ivan", "Bykov", "tiner", "23");

		userRepo.save(user1);
		userRepo.save(user2);
		userRepo.save(user3);
	}
}
