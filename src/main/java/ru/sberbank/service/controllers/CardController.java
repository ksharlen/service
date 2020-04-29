package ru.sberbank.service.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sberbank.service.dto.*;
import ru.sberbank.service.service.ValidService;
import ru.sberbank.service.service.impl.CardServiceImpl;

@RestController
@RequestMapping
public class CardController {
	private final CardServiceImpl cardService;

	private final ValidService validService;

	public CardController(CardServiceImpl cardService, ValidService validService) {
		this.cardService = cardService;
		this.validService = validService;
	}

	@PostMapping(value = "/{login}/cards/")
	public CardDto newCard(@PathVariable String login,
	                       @RequestBody NewCardDto newCardDto) {
		validService.userIsFind(login);
		validService.isEmpty(newCardDto.getName());
		validService.isEmpty(newCardDto.getLastName());
		return cardService.addNewCard(newCardDto, login);
	}

	@PutMapping(value = "/{login}/cards/{cardId}", params = "op=replenish")
	public CardDto replenishCard(@PathVariable String login,
	                             @PathVariable Long cardId,
	                             @RequestBody ReplenishCardDto replenishCardDto) {
		validService.cardIsFind(cardId);
		validService.sumIsValid(replenishCardDto.getIncreaseSumBy());
		return cardService.replenish(replenishCardDto, cardId);
	}


	@PutMapping(value = "/{login}/cards/{cardId}", params = "op=transfer")
	public CardDto transfer(@PathVariable(name = "login") String login,
							@PathVariable(name = "cardId") Long cardId,
							@RequestBody TransferDto transferDto) {
		validService.cardIsFind(transferDto.getIdCardByTo());
		validService.cardIsFind(cardId);
		validService.sumIsValid(transferDto.getTransferSum());
		return (cardService.transfer(transferDto, cardId));
	}

	@GetMapping(value = "/{login}/cards/{cardId}", params = "op=balance")
	public BalanceDto viewBalanceCard(@PathVariable String login, @PathVariable(name = "cardId") Long cardId) {
		validService.cardIsFind(cardId);
		return cardService.viewBalance(cardId);
	}
}
