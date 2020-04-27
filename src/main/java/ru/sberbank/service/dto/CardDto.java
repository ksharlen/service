package ru.sberbank.service.dto;

public class CardDto {
	private final Long id;
	private final Long balance;

	public CardDto() {
		this.id = -1L;
		this.balance = 0L;
	}

	public CardDto(Long id, Long balance) {
		this.id = id;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public Long getBalance() {
		return balance;
	}
}