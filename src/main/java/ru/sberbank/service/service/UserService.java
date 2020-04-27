package ru.sberbank.service.service;

public interface UserService<T> {
	T registration(T user);
}
