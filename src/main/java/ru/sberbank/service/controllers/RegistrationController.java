package ru.sberbank.service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.service.ValidService;
import ru.sberbank.service.service.impl.RegistrationServiceImpl;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	private final RegistrationServiceImpl registrationService;
	private final ValidService validService;

	public RegistrationController(RegistrationServiceImpl registrationService, ValidService validService) {
		this.registrationService = registrationService;
		this.validService = validService;
	}

	@PostMapping
	public UserDto registrationUser(@RequestBody RegistrationDto registrationDto) {
		validService.userAlreadyExist(registrationDto.getLogin());
		return registrationService.registration(registrationDto);
	}
}
