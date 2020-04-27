package ru.sberbank.service.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "login", unique = true)
	private String login;

	// TODO: 27.04.2020 временно, пока не знаю как работать с паролем
	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "user")
	private Set<Card> cards;

	public User() {
	}

	public User(String name, String lastName, String login, String password, Set<Card> cards) {
		this.name = name;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.cards = cards;
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

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object userObj) {
		if (userObj == null)
			return (false);
		if (userObj instanceof User) {
			User user = (User) userObj;

			return this.login.equals(user.getLogin());
		}
		return false;
	}
}