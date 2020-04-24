package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.service.domain.Card;
import ru.sberbank.service.domain.User;
import ru.sberbank.service.service.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private UserService<User, Card> userService;

	@PostMapping("/create")
	public User regUser(@RequestBody User user) {
		userService.registration(user);
		return user;
	}
}
