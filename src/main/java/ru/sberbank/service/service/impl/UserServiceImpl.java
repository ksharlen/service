package ru.sberbank.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.repos.UserRepo;
import ru.sberbank.service.service.UserService;

@Service
public class UserServiceImpl implements UserService<UserDto> {
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDto addNewCard(UserDto user) {
		return null;
	}
}
