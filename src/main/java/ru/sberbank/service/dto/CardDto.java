package ru.sberbank.service.dto;

public class CardDto {
	private final Long id;
	private final Long balance;
	private final String name;
	private final String lastName;

	public CardDto(String name, String lastName, Long id, Long balance) {
		this.id = id;
		this.balance = balance;
		this.name = name;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public Long getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}
}
