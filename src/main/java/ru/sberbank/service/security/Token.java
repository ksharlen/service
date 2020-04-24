package ru.sberbank.service.security;

//это временное решение, на данный момент не знаю как использовать токен в spring
public class Token {
	private Long number;

	public Token(Long number) {
		this.number = number;
	}

	public Long getNumber() {
		return number;
	}
}
