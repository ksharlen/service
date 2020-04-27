package ru.sberbank.service.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByLogin(String login);
}
