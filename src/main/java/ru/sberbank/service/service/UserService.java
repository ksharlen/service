package ru.sberbank.service.service;

import ru.sberbank.service.dto.UserDto;

// TODO: 27.04.2020 временное решение
public interface UserService<T> {
	UserDto addNewCard(T user);
}
