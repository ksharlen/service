package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.service.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
}
