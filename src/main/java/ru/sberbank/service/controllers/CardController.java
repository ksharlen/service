package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.service.domain.Card;
import ru.sberbank.service.service.ServiceCard;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
	@Autowired
	private ServiceCard<Card, Long> serviceCard;

	@PutMapping("/money-transaction")
	public List<Card> moneyTransaction(@RequestParam Card fromCard,
	                                   @RequestParam Card onCard,
	                                   @RequestParam Long sum) {
		return (serviceCard.transferMoney(fromCard, onCard, sum));
	}

	@PostMapping("new-card")
	public Card createNewCard(@RequestParam String name, @RequestParam String lastName) {
		return (serviceCard.createNewCard(name, lastName));
	}

	@PutMapping("recharge-the-balance")
	public Card replenishTheBalance(@RequestParam Card card, Long incBy) {
		return (serviceCard.replenishBalance(card, incBy));
	}

	@PutMapping("get-balance")
	public Long getBalanceCard(@RequestBody Long idCard) {
		return (serviceCard.getBalanceCard(idCard));
	}
}
