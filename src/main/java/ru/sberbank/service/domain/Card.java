package ru.sberbank.service.domain;

public class Card {
	private static Long NUMBER = 0L;

	Long id;
	Long balance;
	String username;
	Long userId;

	public Card() {
		++NUMBER;
	}

	public Card(Long balance, String username) {
		this.id = NUMBER;
		this.balance = balance;
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
}
