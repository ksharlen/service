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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.repos.RegistrationRepo;
import ru.sberbank.service.repos.UserRepo;
import ru.sberbank.service.service.impl.RegistrationServiceImpl;

//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerRegistrationTest {
	@LocalServerPort
	private int randomServerPort;

	@Autowired
	private RegistrationRepo registrationRepo;
	@Autowired
	private RegistrationServiceImpl registrationService;
	@Autowired
	private UserRepo userRepo;

	private final RestTemplate restTemplate = new RestTemplate();
	private final UserDto expectedUser = new UserDto("Alex", "Akinin", "miily", "123");

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void init() {
		userRepo.deleteAll();
//		mockMvc = webAppContextSetup(this.wac).build();
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

	// TODO: 27.04.2020 не могу понять как написать тест на проверку дубликата в бд
	// вылетает исключение, не знаю как пофиксить
	// TODO: 27.04.2020 нужно написать обработчик ошибок для правильного возврата сообщений на фронт
//	@Test // TODO: 28.04.2020 временное решение
//	public void testExceptionDuplicate() {
//		final String url = "http://localhost:" + randomServerPort + "/registration";
//		RegistrationDto user = new RegistrationDto(expectedUser.getName(), expectedUser.getLastName(), expectedUser.getLogin(), expectedUser.getPassword());
//
//		RegistrationDto newUser = new RegistrationDto("Alex", "Akinin", "miily", "123");
//		HttpEntity<RegistrationDto> he = new HttpEntity<>(newUser);
//		ResponseEntity<UserDto> re = restTemplate.postForEntity(url, he, UserDto.class);
//		HttpEntity<RegistrationDto> he1 = new HttpEntity<>(newUser);
//		ResponseEntity<UserDto> re1 = restTemplate.postForEntity(url, he1, UserDto.class);
////		Assert.assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
//	}
}
