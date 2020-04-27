package ru.sberbank.service.dto;

public class UserDto {
	private String name;
	private String lastName;
	private String login;
	private String password; //TODO: временное явление

	public UserDto() {
	}

	public UserDto(String name, String lastName, String login, String password) {
		this.name = name;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
