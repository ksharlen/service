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

	@Column(name = "name")
	private final String name;

	@Column(name = "last_name")
	private final String lastName;

	@Column(name = "balance")
	private Long balance;

	@Column(name = "date_of_create")
	private final Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private final User user;

	@OneToMany(mappedBy = "card")
	private List<TransactionReplenishImpl> transactionReplenish;

	@OneToMany(mappedBy = "card")
	private List<TransactionTransferImpl> transactionTransfer;

	public Card() {
		this.name = "";
		this.lastName = "";
		this.date = new Date();
		this.user = null;
	}

	public Card(String name, String lastName, User user) {
		this.balance = 0L;
		this.user = user;
		this.date = new Date();
		this.name = name;
		this.lastName = lastName;
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

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}
}
