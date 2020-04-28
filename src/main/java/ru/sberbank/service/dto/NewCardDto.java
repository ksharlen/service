package ru.sberbank.service.dto;

public class NewCardDto {
	private String test;

	public NewCardDto() {
	}

	public NewCardDto(String test) {
		this.test = test;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}
