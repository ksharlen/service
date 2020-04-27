package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService<UserDto> {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto addNewCard(UserDto user) {
		return null;
	}
}
