package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.entity.Card;
import ru.sberbank.service.entity.User;
import ru.sberbank.service.exception.ConflictException;
import ru.sberbank.service.repos.CardRepository;
import ru.sberbank.service.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService<User, Card> {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CardRepository cardRepository;

	@Override
	public User registration(User user) {
		User findUser = userRepository.findUserByLogin(user.getLogin());

		if (findUser != null) {
			throw new ConflictException();
		} else {
			userRepository.save(user);
			return (user);
		}
	}

	@Override
	public boolean addNewCard(User user, Card card) {
		card.setUserId(user.getId());
		cardRepository.save(card);
		return (true);
	}
}
