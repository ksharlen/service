package ru.sberbank.service.domain;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long balance;
	private String username;
	private String lastName;
	private Long userId;

	public Card() {
	}

	public Card(String username, String lastName) {
		this.balance = 0L;
		this.lastName = lastName;
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return (this.id);
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isNotThanLess(Long sum) {
		return (this.balance >= sum);
	}

	public boolean increaseBalance(Long incBy) {
		this.balance += incBy;
		return (true);
	}

	public boolean decreaseBalance(Long decBy) {
		if (decBy > this.balance) {
			return (false);
		} else {
			this.balance -= decBy;
			return (true);
		}
	}
}
