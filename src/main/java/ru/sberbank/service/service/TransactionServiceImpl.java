package ru.sberbank.service.service;

import org.springframework.stereotype.Service;
import ru.sberbank.service.entity.Card;
import ru.sberbank.service.entity.transaction.impl.TransactionReplenishImpl;
import ru.sberbank.service.entity.transaction.impl.TransactionTransferImpl;
import ru.sberbank.service.repos.TransactionReplenishRepo;
import ru.sberbank.service.repos.TransactionTransferRepo;

import javax.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService<Card, Long> {
	private final TransactionReplenishRepo transactionReplenishRepo;
	private final TransactionTransferRepo transactionTransferRepo;

	public TransactionServiceImpl(TransactionReplenishRepo transactionReplenishRepo, TransactionTransferRepo transactionTransferRepo) {
		this.transactionReplenishRepo = transactionReplenishRepo;
		this.transactionTransferRepo = transactionTransferRepo;
	}

	@Transactional
	public boolean transferTransaction(Card cardFrom, Card cardTo, Long sum) {
		TransactionTransferImpl transactionTransfer = createTransactionTransfer(cardFrom, cardTo, sum);
		transactionTransferRepo.save(transactionTransfer);
		TransactionReplenishImpl transactionReplenish = createTransactionReplenish(cardTo, sum);
		transactionReplenishRepo.save(transactionReplenish);
		return true;
	}

	@Transactional
	public boolean replenishTransaction(Card card, Long sum) {
		TransactionReplenishImpl transactionReplenish = createTransactionReplenish(card, sum);
		transactionReplenishRepo.save(transactionReplenish);
		return true;
	}

	private TransactionReplenishImpl createTransactionReplenish(Card card, Long sum) {
		return new TransactionReplenishImpl(sum, card.getBalance(), card);
	}

	private TransactionTransferImpl createTransactionTransfer(Card cardFrom, Card cardTo, Long sum) {
		return new TransactionTransferImpl(cardFrom.getId(), cardTo.getId(), sum, cardFrom.getBalance(), cardFrom);
	}
}
