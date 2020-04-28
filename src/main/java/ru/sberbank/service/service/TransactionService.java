package ru.sberbank.service.service;

public interface TransactionService<T, S> {
	boolean transferTransaction(T cardFrom, T cardTo, S sum);
	boolean replenishTransaction(T card, S sum);
}
