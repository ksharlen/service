package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.exception.DuplicateRecordException;
import ru.sberbank.service.repos.UserRepository;
import ru.sberbank.service.service.RegistrationServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/add")
public class TestController {
	@Autowired
	private RegistrationServiceImpl registrationService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public void addUser() {
		RegistrationDto registrationDto1 = new RegistrationDto("Alexandr", "Akinin", "miily", "123");
		RegistrationDto registrationDto2 = new RegistrationDto("Alexandr", "Akinin", "ksho", "1234");
		RegistrationDto registrationDto3 = new RegistrationDto("Alexandr", "Akinin", "qwery", "1235");
		registrationService.registration(registrationDto1);
		registrationService.registration(registrationDto2);
		registrationService.registration(registrationDto3);
	}

	@GetMapping("/list")
	public List<UserDto> getUser() {
		List<User> users = userRepository.findAll();
		List<UserDto> usersDto = new ArrayList<>();

		throw new DuplicateRecordException("this is error");
//		for (User user : users) {
//			usersDto.add(userToUserDto(user));
//		}
//		return usersDto;
	}

	private UserDto userToUserDto(User user) {
		return new UserDto(user.getName(), user.getLastName(), user.getLogin(), user.getPassword());
	}
}
