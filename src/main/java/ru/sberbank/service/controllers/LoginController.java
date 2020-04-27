package ru.sberbank.service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.service.dto.LoginDto;
import ru.sberbank.service.dto.UserDto;

@RestController
@RequestMapping("/login")
public class LoginController {
	// TODO: 27.04.2020 временное явление, логику пока не сделал
	@PostMapping
	public UserDto login(LoginDto loginDto) {
		return (new UserDto());
	}
}
