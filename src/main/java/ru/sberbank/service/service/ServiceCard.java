package ru.sberbank.service.service;

public interface ServiceCard<C, S> {
	C createNewCard(String name, String lastName);
	boolean transferMoney(C fromCard, C onCard, S sum);
	boolean replenishBalance(C card, S increaseBy);
	S getBalanceCard(C card);
}
