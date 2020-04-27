package ru.sberbank.service.service;

public interface RegistrationService<T, S> {
	S registration(T newUser);
}
