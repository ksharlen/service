package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.exception.DuplicateRecordException;
import ru.sberbank.service.service.RegistrationServiceImpl;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private RegistrationServiceImpl registrationService;

	@PostMapping
	public UserDto registrationUser(@RequestBody RegistrationDto registrationDto) throws DuplicateRecordException {
		// TODO: 27.04.2020 validation registration
		return registrationService.registration(registrationDto);
	}
}
