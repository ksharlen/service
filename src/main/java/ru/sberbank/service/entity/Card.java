package ru.sberbank.service.entity;

import ru.sberbank.service.entity.transaction.impl.TransactionReplenishImpl;
import ru.sberbank.service.entity.transaction.impl.TransactionTransferImpl;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "balance")
	private Long balance;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "card")
	private List<TransactionReplenishImpl> transactionReplenish;

	// TODO: 27.04.2020 подумать над коллекцией
	@OneToMany(mappedBy = "card")
	private List<TransactionTransferImpl> transactionTransfer;

	public Card() {
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
}
