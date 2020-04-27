package ru.sberbank.service.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.service.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	Card getCardById(Long id);
}
