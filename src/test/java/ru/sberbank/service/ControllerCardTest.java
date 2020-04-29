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
import ru.sberbank.service.dto.*;
import ru.sberbank.service.entity.Card;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.repos.CardRepo;
import ru.sberbank.service.repos.UserRepo;

// TODO: 29.04.2020 отрефакторить!

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
// TODO: 29.04.2020 Чистит бд перед каждым тестом
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControllerCardTest {
	@LocalServerPort
	private int randomServerPort;

	@Autowired
	private CardRepo cardRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void initTest() {
	}

	@Test
	public void addNewCardTest() {
		String testLoginUser = "miily";
		final String baseUrl = getUrlCard(testLoginUser);
		NewCardDto newCardDto = new NewCardDto("Alexandr", "Akinin");

		createUser();
		HttpEntity<NewCardDto> request = new HttpEntity<>(newCardDto);
		ResponseEntity<CardDto> response = restTemplate.postForEntity(baseUrl, request, CardDto.class);

		Assert.assertNotNull(response.getBody());
		Assert.assertEquals("Alexandr", response.getBody().getName());
		Assert.assertEquals("Akinin", response.getBody().getLastName());
		Assert.assertEquals(0, (long) response.getBody().getBalance());
	}

	@Test
	public void replenishCardTest() {
		User user = createUserAndUserCard();
		Long idCard = user.getCards().get(0).getId();
		final String baseUrl = getUrlCard("miily", idCard, "?op=replenish");
		ReplenishCardDto replenishCardDto = new ReplenishCardDto(10000L);

		HttpEntity<ReplenishCardDto> request = new HttpEntity<>(replenishCardDto);
		restTemplate.put(baseUrl, request, CardDto.class);
		Card card = cardRepo.findCardById(idCard);

		Assert.assertEquals(new Long(10000), card.getBalance());
	}


	@Test
	public void transferTest() {
		User testUser = createUserAndTwoCardsWithMoney();
		Card cardFromTest = testUser.getCards().get(0);
		Card cardToTest = testUser.getCards().get(1);
		Long transferSum = 500L;
		String baseUrl = getUrlCard(testUser.getLogin(), cardFromTest.getId(), "?op=transfer");
		TransferDto transferDto = new TransferDto(cardToTest.getId(), transferSum);

		HttpEntity<TransferDto> request = new HttpEntity<>(transferDto);
		restTemplate.put(baseUrl, request, TransferDto.class);
		cardFromTest = cardRepo.findCardById(cardFromTest.getId());
		cardToTest = cardRepo.findCardById(cardToTest.getId());

		Assert.assertNotNull(cardFromTest);
		Assert.assertNotNull(cardToTest);
		Assert.assertEquals(500L, cardFromTest.getBalance().longValue());
		Assert.assertEquals(1500L, cardToTest.getBalance().longValue());
	}

	@Test
	public void getBalanceTest() {
		User userTest = createUserWithOneCardWithMoney();
		Card userCard = userTest.getCards().get(0);
		Long expectedBalance = 523L;
		String baseUrl = getUrlCard(userTest.getLogin(), userCard.getId(), "?op=balance");

		BalanceDto balanceDto = restTemplate.getForObject(baseUrl, BalanceDto.class);

		Assert.assertNotNull(balanceDto);
		Assert.assertEquals(expectedBalance, balanceDto.getBalance());
	}

	private void createUser() {
		User user = new User("Alexandr", "Akinin", "miily", "123");

		userRepo.save(user);
	}

	private User createUserAndUserCard() {
		User user = new User("Alexandr", "Akinin", "miily", "123");
		Card card = new Card("ALEXANDR", "AKININ", user);

		userRepo.save(user);
		cardRepo.save(card);
		return userRepo.findUserByLogin("miily");
	}

	private User createUserAndTwoCardsWithMoney() {
		User user = new User("Alexandr", "Akinin", "miily", "123");
		Card card1 = new Card("ALEXANDR", "AKININ", user);
		Card card2 = new Card("ALEXANDR", "AKININ", user);


		card1.setBalance(1000L);
		card2.setBalance(1000L);
		userRepo.save(user);
		cardRepo.save(card1);
		cardRepo.save(card2);
		return userRepo.findUserByLogin("miily");
	}

	private User createUserWithOneCardWithMoney() {
		User user = new User("Alexandr", "Akinin", "miily", "123");
		Card card = new Card("ALEXANDR", "AKININ", user);

		card.setBalance(523L);
		userRepo.save(user);
		cardRepo.save(card);
		return userRepo.findUserByLogin("miily");
	}

	private String getUrlCard(String login) {
		return "http://localhost:" + randomServerPort + "/" + login + "/cards/";
	}

	private String getUrlCard(String login, Long idCard) {
		return getUrlCard(login) + idCard;
	}

	private String getUrlCard(String login, Long idCard, String request) {
		return getUrlCard(login, idCard) + request;
	}
}
