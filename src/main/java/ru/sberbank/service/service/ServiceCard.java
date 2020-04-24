package ru.sberbank.service.service;

public interface ServiceCard<C, S> {
	C createNewCard(String name, String lastName);
	C transferMoney(S fromCard, S onCard, S sum);
	C replenishBalance(C card, S increaseBy);
	S getBalanceCard(S idCard);
	C getCard(S idCard);
}
