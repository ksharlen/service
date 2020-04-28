package ru.sberbank.service.entity;

import ru.sberbank.service.entity.transaction.impl.TransactionReplenishImpl;
import ru.sberbank.service.entity.transaction.impl.TransactionTransferImpl;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "balance")
	private Long balance;

	@Column(name = "date_of_create")
	private final Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private final User user;

	@OneToMany(mappedBy = "card")
	private List<TransactionReplenishImpl> transactionReplenish;

	// TODO: 27.04.2020 подумать над коллекцией
	@OneToMany(mappedBy = "card")
	private List<TransactionTransferImpl> transactionTransfer;

	public Card(User user) {
		this.balance = 0L;
		this.user = user;
		this.date = new Date();
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public List<TransactionReplenishImpl> getTransactionReplenish() {
		return transactionReplenish;
	}

	public void setTransactionReplenish(List<TransactionReplenishImpl> transactionReplenish) {
		this.transactionReplenish = transactionReplenish;
	}

	public List<TransactionTransferImpl> getTransactionTransfer() {
		return transactionTransfer;
	}

	public void setTransactionTransfer(List<TransactionTransferImpl> transactionTransfer) {
		this.transactionTransfer = transactionTransfer;
	}

	public Date getDate() {
		return date;
	}
}
