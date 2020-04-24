package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.domain.Card;
import ru.sberbank.service.repos.CardRepository;

import java.util.Arrays;
import java.util.List;

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
	public List<Card> transferMoney(Card fromCard, Card onCard, Long sum) {
		if (fromCard.isNotThanLess(sum)) {
			fromCard.decreaseBalance(sum);
			onCard.increaseBalance(sum);
			cardRepository.save(fromCard);
			cardRepository.save(onCard);
		} else {
			//todo: exception
		}
		return (Arrays.asList(fromCard, onCard));
	}

	@Override
	public Card replenishBalance(Card card, Long increaseBy) {
		card.increaseBalance(increaseBy);
		cardRepository.save(card);
		return (cardRepository.getCardById(card.getId()));
	}

	@Override
	public Long getBalanceCard(Long idCard) {
		return (cardRepository.getCardById(idCard).getBalance());
	}
}
