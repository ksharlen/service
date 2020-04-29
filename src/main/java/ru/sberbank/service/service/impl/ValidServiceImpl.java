package ru.sberbank.service.service.impl;

import org.springframework.stereotype.Service;
import ru.sberbank.service.exception.BadRequestException;
import ru.sberbank.service.exception.DuplicateRecordException;
import ru.sberbank.service.exception.NotFoundException;
import ru.sberbank.service.repos.CardRepo;
import ru.sberbank.service.repos.UserRepo;
import ru.sberbank.service.service.ValidService;

@Service
public class ValidServiceImpl implements ValidService {
	private final CardRepo cardRepo;
	private final UserRepo userRepo;

	public ValidServiceImpl(CardRepo cardRepo, UserRepo userRepo) {
		this.cardRepo = cardRepo;
		this.userRepo = userRepo;
	}

	@Override
	public boolean userIsFind(String login) {
		if (userRepo.findUserByLogin(login) == null) {
			throw new NotFoundException("Пользователь с таким логином не найден");
		} else {
			return true;
		}
	}

	@Override
	public boolean sumIsValid(Long sum) {
		if (sum <= 0) {
			throw new BadRequestException("Невалидная сумма");
		} else {
			return true;
		}
	}

	@Override
	public boolean cardIsFind(Long cardId) {
		if (cardRepo.findCardById(cardId) == null) {
			throw new NotFoundException("Карта с таким id не найдена");
		} else {
			return true;
		}
	}

	@Override
	public boolean userAlreadyExist(String login) {
		if (userRepo.findUserByLogin(login) != null) {
			throw new DuplicateRecordException("Пользователь с таким логином уже существует");
		} else {
			return true;
		}
	}

	@Override
	public boolean isEmpty(String str) {
		if (str == null) {
			throw new BadRequestException("Пустая строка");
		} else if (str.length() == 0) {
			throw new BadRequestException("Пустая строка");
		} else {
			return true;
		}
	}
}
