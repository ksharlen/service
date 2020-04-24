package ru.sberbank.service.service;

public interface UserService<U, C> {
	U registration(U user);
	boolean addNewCard(U user, C card); //todo: думаю будет перенесенно в сервис карт.
}
