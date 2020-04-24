package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.domain.Card;
import ru.sberbank.service.repos.CardRepository;

@Service
public class ServiceCardImpl implements ServiceCard<Card, Long>{
	@Autowired
	private CardRepository cardRepository;

	@Override
	public Card createNewCard(String name, String lastName) {
		Card card = new Card(name, lastName);
		cardRepository.save(card);
		return (card);
	}

	@Override
	public boolean transferMoney(Card fromCard, Card onCard, Long sum) {
		if (fromCard.isNotThanLess(sum)) {
			fromCard.decreaseBalance(sum);
			onCard.increaseBalance(sum);
			cardRepository.save(fromCard);
			cardRepository.save(onCard);
			return (true);
		} else {
			//todo: exception
			return (false);
		}
	}

	@Override
	public boolean replenishBalance(Card card, Long increaseBy) {
		return (card.increaseBalance(increaseBy));
	}

	@Override
	public Long getBalanceCard(Card card) {
		return (card.getBalance());
	}
}
