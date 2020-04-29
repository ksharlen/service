package ru.sberbank.service.entity.transaction.impl;

import ru.sberbank.service.entity.Card;
import ru.sberbank.service.entity.transaction.Transaction;

import javax.persistence.*;
import java.util.Date;

// TODO: 28.04.2020 пересмотреть
@Entity
@Table(name = "transaction_replenish")
public class TransactionReplenishImpl implements Transaction {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "date")
	private final Date date;

	@Column(name = "increased_by")
	private Long incBy;

	@Column(name = "balance_after_inc")
	private Long balanceAfter;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id")
	private Card card;

	public TransactionReplenishImpl() {
		this.date = new Date();
	}

	public TransactionReplenishImpl(Long incBy, Long balanceAfter, Card card) {
		this();
		this.incBy = incBy;
		this.balanceAfter = balanceAfter;
		this.card = card;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Long getIncBy() {
		return incBy;
	}

	public Long getBalanceAfter() {
		return balanceAfter;
	}

	public Card getCard() {
		return card;
	}
}
