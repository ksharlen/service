package ru.sberbank.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@GetMapping
	public UserDto registrationUser(@RequestBody RegistrationDto registrationDto) {
		UserDto newUser = new UserDto();
		// TODO: 27.04.2020 validation registration
		//add new user
		//return dtoUser
		//UserDto newUser = serviceUser.getNewUser();
		return newUser;
	}
}
