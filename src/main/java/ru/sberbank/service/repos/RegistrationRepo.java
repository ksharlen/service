package ru.sberbank.service.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.service.entity.User;

public interface RegistrationRepo extends JpaRepository<User, Long> {
}
