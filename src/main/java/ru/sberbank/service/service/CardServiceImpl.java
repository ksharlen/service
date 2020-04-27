package ru.sberbank.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.service.entity.Card;
import ru.sberbank.service.repos.CardRepository;

@Service
public class CardServiceImpl implements CardService<Card, Long> {
	@Autowired
	private CardRepository cardRepository;

	@Override
	public Card createNewCard(String name, String lastName) {
		Card card = new Card(name, lastName);
		cardRepository.save(card);
		return (card);
	}

	@Override
	public Card transferMoney(Long fromCard, Long onCard, Long sum) {
		Card cardFrom = cardRepository.getCardById(fromCard);
		Card cardOn = cardRepository.getCardById(onCard);
		if (cardFrom.isNotThanLess(sum)) {
			cardFrom.decreaseBalance(sum);
			cardOn.increaseBalance(sum);
			cardRepository.save(cardFrom);
			cardRepository.save(cardOn);
		} else {
			//todo: exception
		}
		return (cardFrom);
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

	@Override
	public Card getCard(Long idCard) {
		return (cardRepository.getCardById(idCard));
	}
}
