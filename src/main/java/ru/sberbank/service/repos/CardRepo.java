package ru.sberbank.service.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.service.entity.Card;

public interface CardRepo extends JpaRepository<Card, Long> {
	Card getCardById(Long id);
}
