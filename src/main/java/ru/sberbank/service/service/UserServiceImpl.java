package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.domain.User;
import ru.sberbank.service.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User addNewUser(User user) {
		User findUser = userRepository.findUserByLogin(user.getLogin());

		if (findUser != null) {
			//TODO: error
			return (null);
		} else {
			userRepository.save(user);
			return (user);
		}
	}
}
