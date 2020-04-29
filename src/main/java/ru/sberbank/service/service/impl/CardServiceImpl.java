package ru.sberbank.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.service.dto.*;
import ru.sberbank.service.entity.Card;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.exception.BadRequestException;
import ru.sberbank.service.repos.CardRepo;
import ru.sberbank.service.repos.UserRepo;
import ru.sberbank.service.service.CardService;

// TODO: 27.04.2020 наследоваться от астрактного DTO
// TODO: 28.04.2020 необходимо разделить на два сервиса, один отвечает денежную обработку, второй за
// TODO: манипуляции с картами

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardRepo cardRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TransactionServiceImpl transactionService;

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE) // TODO: 29.04.2020 не уверен на счет serializable
	public CardDto replenish(ReplenishCardDto replenishCardDto, Long idCard) {
		Card card = cardRepo.findCardById(idCard);
		card.setBalance(card.getBalance() + replenishCardDto.getIncreaseSumBy());
		card = cardRepo.save(card);
		transactionService.replenishTransaction(card, replenishCardDto.getIncreaseSumBy());

		return convertCardToCardDto(card);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public CardDto transfer(TransferDto transferDto, Long idCard) {
		if (transferDto.getIdCardByTo().equals(idCard)) {
			throw new BadRequestException("Невозможно сделать перевод, номера карт совпадают");
		}
		Card cardFrom = cardRepo.findCardById(idCard);
		if (transferDto.getTransferSum() > cardFrom.getBalance()) {
			throw new BadRequestException("Недостаточно средств на карте для перевода");
		}

		Card cardTo = cardRepo.findCardById(transferDto.getIdCardByTo());
		cardFrom.setBalance(cardFrom.getBalance() - transferDto.getTransferSum());
		cardTo.setBalance(cardTo.getBalance() + transferDto.getTransferSum());
		cardFrom = cardRepo.save(cardFrom);
		cardTo = cardRepo.save(cardTo);
		transactionService.transferTransaction(cardFrom, cardTo, transferDto.getTransferSum());

		return convertCardToCardDto(cardFrom);
	}

	@Override
	@Transactional(readOnly = true)
	public BalanceDto viewBalance(Long idCard) {
		return new BalanceDto(cardRepo.findCardById(idCard).getBalance());
	}

	@Override
	@Transactional
	public CardDto addNewCard(NewCardDto newCardDto, String login) {
		User user = userRepo.findUserByLogin(login);
		Card card = new Card(newCardDto.getName(), newCardDto.getLastName(), user);
		card = cardRepo.save(card);

		return convertCardToCardDto(card);
	}

	// TODO: 27.04.2020 временное решение пока не воткнул маппер
	private CardDto convertCardToCardDto(Card card) {
		return new CardDto(card.getName(), card.getLastName(), card.getId(), card.getBalance());
	}
}
