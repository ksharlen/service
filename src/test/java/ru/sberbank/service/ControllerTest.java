package ru.sberbank.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.repos.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {
	@LocalServerPort
	int randomServerPort;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateUser() {
		RestTemplate restTemplate = new RestTemplate();
		final String url = "http://localhost:" + randomServerPort + "/registration/create";
		User userTest = new User("Alexandr", "Akinin", "Sergeevich", "alex", "123");
		User newUser = null;

		HttpEntity<User> he = new HttpEntity<>(userTest);
		ResponseEntity<User> result = restTemplate.postForEntity(url, he, User.class);
		newUser = result.getBody();
		Assert.assertSame(HttpStatus.OK, result.getStatusCode());
		Assert.assertNotNull(newUser);
		Assert.assertEquals(userTest.getName(), newUser.getName());
		Assert.assertTrue(equalsUsers(userTest, newUser));
	}

	@Test
	public void tmpTest() {
		User user = new User("Alexandr", "Akinin", "Sergeevich", "alex", "123");
		User userTest;

		userRepository.save(user);
		userTest = userRepository.findUserByLogin(user.getLogin());
		Assert.assertTrue(equalsUsers(user, userTest));
	}

	private boolean equalsUsers(User expected, User actual) {
		return expected.getName().equals(actual.getName()) &&
				expected.getLastName().equals(actual.getLastName()) &&
				expected.getLogin().equals(actual.getLogin()) &&
				expected.getPatronymic().equals(actual.getPatronymic()) &&
				expected.getPassword().equals(actual.getPassword());
	}
}