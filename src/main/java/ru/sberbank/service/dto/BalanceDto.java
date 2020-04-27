package ru.sberbank.service.dto;

public class BalanceDto {
	private final Long balance;

	public BalanceDto() {
		this.balance = 0L;
	}

	public BalanceDto(Long balance) {
		this.balance = balance;
	}

	public Long getBalance() {
		return balance;
	}
}
