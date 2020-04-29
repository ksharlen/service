package ru.sberbank.service.service.impl;

import org.springframework.stereotype.Service;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.exception.DuplicateRecordException;
import ru.sberbank.service.repos.RegistrationRepo;
import ru.sberbank.service.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService<RegistrationDto, UserDto> {
	private final RegistrationRepo registrationRepo;

	public RegistrationServiceImpl(RegistrationRepo registrationRepo) {
		this.registrationRepo = registrationRepo;
	}

	@Override
	public UserDto registration(RegistrationDto newUser) throws DuplicateRecordException {
		User user = registrationRepo.save(new User(newUser.getName(), newUser.getLastName(), newUser.getLogin(), newUser.getPassword()));
		return new UserDto(user.getName(), user.getLastName(), user.getLogin(), user.getPassword());
	}
}
