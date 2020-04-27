package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.dto.RegistrationDto;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.exception.DuplicateRecordException;
import ru.sberbank.service.repos.RegistrationRepo;
import ru.sberbank.service.repos.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService<RegistrationDto, UserDto>{
	@Autowired
	private RegistrationRepo registrationRepo;
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto registration(RegistrationDto newUser) throws DuplicateRecordException {
		User user = new User(newUser.getName(), newUser.getLastName(), newUser.getLogin(), newUser.getPassword());
		if (userRepository.findUserByLogin(newUser.getLogin()) != null)
			throw new DuplicateRecordException("Такой пользователь уже существует");
		registrationRepo.save(user); // TODO: 27.04.2020 нужно проверить нет ли уже такого пользователя
		return (new UserDto(user.getName(), user.getLastName(), user.getLogin(), user.getPassword()));
	}
}
