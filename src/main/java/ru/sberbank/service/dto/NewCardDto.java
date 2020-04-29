package ru.sberbank.service.dto;

public class NewCardDto {
	private final String name;
	private final String lastName;

	public NewCardDto() {
		this.name = "";
		this.lastName = "";
	}

	public NewCardDto(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}
}
