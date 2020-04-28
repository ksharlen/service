package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.dto.UserDto;
import ru.sberbank.service.repos.UserRepo;

@Service
public class UserServiceImpl implements UserService<UserDto> {
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDto addNewCard(UserDto user) {
		return null;
	}
}
