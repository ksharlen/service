package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.dto.*;
import ru.sberbank.service.entity.Card;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.entity.transaction.Transaction;
import ru.sberbank.service.entity.transaction.impl.TransactionReplenishImpl;
import ru.sberbank.service.entity.transaction.impl.TransactionTransferImpl;
import ru.sberbank.service.exception.BadRequestException;
import ru.sberbank.service.exception.NotFoundException;
import ru.sberbank.service.repos.CardRepo;
import ru.sberbank.service.repos.TransactionReplenishRepo;
import ru.sberbank.service.repos.TransactionTransferRepo;
import ru.sberbank.service.repos.UserRepo;

import javax.transaction.Transactional;
import java.util.List;
// TODO: 27.04.2020 наследоваться от астрактного DTO
// TODO: 28.04.2020 необходимо разделить на два сервиса, один отвечает денежную обработку, второй за
// TODO: манипуляции с картами

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardRepo cardRepo;
	@Autowired
	private TransactionReplenishRepo transactionReplenishRepo;
	@Autowired
	private TransactionTransferRepo transactionTransferRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional
	public CardDto replenish(ReplenishCardDto replenishCardDto, Long idCard) {
		Card card = cardRepo.getCardById(idCard);
		if (card == null) {
			throw new NotFoundException("Карта с таким id не найдена");
		} else if (replenishCardDto.getIncreaseSumBy() <= 0) {
			throw new BadRequestException("Указана неверная сумма для пополнения");
		}
		// TODO: 28.04.2020 перевести в новый сервис
		card.setBalance(card.getBalance() + replenishCardDto.getIncreaseSumBy());
		TransactionReplenishImpl transaction = createTransactionReplenish(replenishCardDto, card);
		putTransactionToCard(card, transaction);
		card = cardRepo.save(card);
		transactionReplenishRepo.save(transaction);
		return convertCardToCardDto(card);
	}

	@Override
	@Transactional
	public CardDto transfer(TransferDto transferDto, Long idCard) {
		// TODO: 27.04.2020 придумать как обработать два одновременных
		// TODO: 27.04.2020 запроса на перевод
		// TODO: 27.04.2020 проверяем баланс, после кидаем код или переводим
		return null;
	}

	@Override
	public BalanceDto viewBalance(Long idCard) {
		Card card = cardRepo.getCardById(idCard);
		if (card == null) {
			throw new NotFoundException("Карта с таким id не найдена");
		}
		return new BalanceDto(card.getBalance());
	}

	@Override
	@Transactional
	public CardDto addNewCard(NewCardDto newCardDto, String login) {
		User user = userRepo.findUserByLogin(login);
		if (user == null) {
			throw new NotFoundException("Пользователь с таким логином не найден");
		}
		Card card = new Card(user);
		// TODO: 28.04.2020 надо ли пользователю добовлять новую карту
		card = cardRepo.save(card);
		return convertCardToCardDto(card);
	}

	// TODO: 27.04.2020 временное решение пока не воткнул маппер
	private CardDto convertCardToCardDto(Card card) {
		return new CardDto(card.getId(), card.getBalance());
	}

	private TransactionReplenishImpl createTransactionReplenish(ReplenishCardDto replenishCardDto, Card card) {
		return new TransactionReplenishImpl(replenishCardDto.getIncreaseSumBy(), card.getBalance(), card);
	}

	// TODO: 28.04.2020 временное решение
	private void putTransactionToCard(Card card, Transaction transaction) {
		if (transaction instanceof TransactionReplenishImpl) {
			List<TransactionReplenishImpl> transactions = card.getTransactionReplenish();
			transactions.add((TransactionReplenishImpl) transaction);
		} else if (transaction instanceof TransactionTransferImpl) {
			List<TransactionTransferImpl> transactions = card.getTransactionTransfer();
			transactions.add((TransactionTransferImpl) transaction);
		}
	}
}
