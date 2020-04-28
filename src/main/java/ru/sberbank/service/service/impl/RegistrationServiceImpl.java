package ru.sberbank.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.exception.DuplicateRecordException;
import ru.sberbank.service.repos.RegistrationRepo;
import ru.sberbank.service.repos.UserRepo;
import ru.sberbank.service.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService<RegistrationDto, UserDto> {
	@Autowired
	private RegistrationRepo registrationRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
//	@ExceptionHandler({DuplicateRecordException.class})
	public UserDto registration(RegistrationDto newUser) throws DuplicateRecordException {
		if (userRepo.findUserByLogin(newUser.getLogin()) != null) {
			throw new DuplicateRecordException("Такая запись уже существует");
		} else {
		User user = new User(newUser.getName(), newUser.getLastName(), newUser.getLogin(), newUser.getPassword());
			registrationRepo.save(user); // TODO: 27.04.2020 нужно проверить нет ли уже такого пользователя
		return (new UserDto(user.getName(), user.getLastName(), user.getLogin(), user.getPassword()));
		}
	}
}
