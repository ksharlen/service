package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.dto.CardDto;
import ru.sberbank.service.dto.ReplenishCardDto;
import ru.sberbank.service.dto.TransferDto;
import ru.sberbank.service.entity.Card;
import ru.sberbank.service.entity.transaction.impl.TransactionReplenishImpl;
import ru.sberbank.service.repos.CardRepo;
import ru.sberbank.service.repos.TransactionReplenishRepo;
import ru.sberbank.service.repos.TransactionTransferRepo;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardRepo cardRepo;
	@Autowired
	private TransactionReplenishRepo transactionReplenishRepo;
	@Autowired
	private TransactionTransferRepo transactionTransferRepo;

	@Override
	@Transactional
	public CardDto replenish(ReplenishCardDto replenishCardDto, Long idCard) {
		Card card = cardRepo.getCardById(idCard);
		// TODO: 27.04.2020 проверить есть ли карта
		// TODO: 27.04.2020 думаю нужно проверить еще на валидную сумму
		card.setBalance(card.getBalance() + replenishCardDto.getIncreaseSumBy());
		card = cardRepo.save(card);
		TransactionReplenishImpl transaction = createTransactionReplenish(replenishCardDto, card);
		transactionReplenishRepo.save(transaction);
		return cardToCardDto(card);
	}

	@Override
	public CardDto transfer(TransferDto transferDto, Long idCard) {
		// TODO: 27.04.2020 придумать как обработать два одновременных
		// TODO: 27.04.2020 запроса на перевод
		// TODO: 27.04.2020 проверяем баланс, после кидаем код или переводим
		return null;
	}

	@Override
	public CardDto viewBalance(Long idCard) {
		Card card = cardRepo.getCardById(idCard);
		// TODO: 27.04.2020 если нет, бросить ошибку
		return cardToCardDto(card);
	}

	// TODO: 27.04.2020 временное решение пока не воткнул маппер
	private CardDto cardToCardDto(Card card) {
		return new CardDto(card.getId(), card.getBalance());
	}

	private TransactionReplenishImpl createTransactionReplenish(ReplenishCardDto replenishCardDto, Card card) {
		return new TransactionReplenishImpl(new Date(), replenishCardDto.getIncreaseSumBy(), card.getBalance(), card);
	}
}
