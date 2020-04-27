package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.entity.Card;
import ru.sberbank.service.repos.CardRepository;

@Service
public class CardServiceImpl implements CardService<Card, Long> {
	@Autowired
	private CardRepository cardRepository;

	// TODO: 27.04.2020 временное решение
	@Override
	public Card createNewCard(String name, String lastName) {
		return null;
	}

	@Override
	public Card transferMoney(Long fromCard, Long onCard, Long sum) {
		return null;
	}

	@Override
	public Card replenishBalance(Card card, Long increaseBy) {
		return null;
	}

	@Override
	public Long getBalanceCard(Long idCard) {
		return null;
	}

	@Override
	public Card getCard(Long idCard) {
		return null;
	}
}
