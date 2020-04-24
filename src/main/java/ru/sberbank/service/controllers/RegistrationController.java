package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.service.domain.User;
import ru.sberbank.service.service.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public User regUser(@RequestBody User user) {
		userService.registration(user);
		return user;
	}

	@GetMapping
	public User test() {
		return (new User("alex", "Akinin", "Sergeevich", "log", "pass"));
	}
}

//TODO: Добовлять пользователей
//TODO: postman (прочитать)
//TODO: curl