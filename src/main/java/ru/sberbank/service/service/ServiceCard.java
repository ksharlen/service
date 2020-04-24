package ru.sberbank.service.service;

import java.util.List;

public interface ServiceCard<C, S> {
	C createNewCard(String name, String lastName);
	List<C> transferMoney(C fromCard, C onCard, S sum);
	C replenishBalance(C card, S increaseBy);
	S getBalanceCard(S idCard);
}
