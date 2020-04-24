package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.service.domain.Card;
import ru.sberbank.service.service.ServiceCard;

@RestController
@RequestMapping("/{userId}/cards")
public class CardController {
	@Autowired
	private ServiceCard<Card, Long> serviceCard;

	@GetMapping("/{CardId}")
	public Card getCard(@PathVariable String userId, @PathVariable String CardId) {
		return (serviceCard.getCard(Long.parseLong(CardId)));
	}


	@PutMapping("/transaction")
	public Card moneyTransaction(@PathVariable String userId,
	                             @RequestParam Long idFromCard,
                                 @RequestParam Long idOnCard,
                                 @RequestParam Long sum) {
		return (serviceCard.transferMoney(idFromCard, idOnCard, sum));
	}

	@PostMapping("/new-card")
	public Card createNewCard(@PathVariable String userId,
	                          @RequestParam String name,
	                          @RequestParam String lastName) {
		return (serviceCard.createNewCard(name, lastName));
	}

	@PutMapping("/balance/recharge-the-balance")
	public Card replenishTheBalance(@PathVariable String userId,
									@RequestParam Card card, Long incBy) {
		return (serviceCard.replenishBalance(card, incBy));
	}

	@PutMapping("/balance/{cardId}")
	public Long getBalanceCard(@PathVariable String userId,
	                           @PathVariable String cardId) {
		return (serviceCard.getBalanceCard(Long.parseLong(cardId)));
	}
}
