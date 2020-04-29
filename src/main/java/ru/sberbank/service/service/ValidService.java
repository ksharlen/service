package ru.sberbank.service.service;

public interface ValidService {
	boolean userIsFind(String login);
	boolean sumIsValid(Long sum);
	boolean cardIsFind(Long cardId);
	boolean userAlreadyExist(String login);
	boolean isEmpty(String str);
}
