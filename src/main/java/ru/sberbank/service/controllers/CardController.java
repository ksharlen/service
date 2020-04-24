package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.service.domain.Card;
import ru.sberbank.service.service.CardService;

@RestController
@RequestMapping("/user/{userId}/cards")
public class CardController {
	@Autowired
	private CardService<Card, Long> cardService;

	@GetMapping("/{CardId}")
	public Card getCard(@PathVariable String userId, @PathVariable String CardId) {
		return (cardService.getCard(Long.parseLong(CardId)));
	}


	@PutMapping("/transaction")
	public Card moneyTransaction(@PathVariable String userId,
	                             @RequestParam Long idFromCard,
                                 @RequestParam Long idOnCard,
                                 @RequestParam Long sum) {
		return (cardService.transferMoney(idFromCard, idOnCard, sum));
	}

	@PostMapping("/new-card")
	public Card createNewCard(@PathVariable String userId,
	                          @RequestParam String name,
	                          @RequestParam String lastName) {
		return (cardService.createNewCard(name, lastName));
	}

	@PutMapping("/balance/recharge-the-balance")
	public Card replenishTheBalance(@PathVariable String userId,
									@RequestParam Card card, Long incBy) {
		return (cardService.replenishBalance(card, incBy));
	}

	@PutMapping("/balance/{cardId}")
	public Long getBalanceCard(@PathVariable String userId,
	                           @PathVariable String cardId) {
		return (cardService.getBalanceCard(Long.parseLong(cardId)));
	}
}
