package ru.sberbank.service.service;

import ru.sberbank.service.domain.Card;

public interface Service {
	boolean transferMoney(Card fromCard, Card onMap, Long sum);
	boolean replenishBalance(Card card, Long increaseBy);
}
