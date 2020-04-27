package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.service.dto.*;
import ru.sberbank.service.service.CardServiceImpl;

@RestController
@RequestMapping("/{login}/cards")
public class CardController {
	@Autowired
	private CardServiceImpl cardService;

	// TODO: 27.04.2020 в разработке
	@PostMapping("/new/")
	public CardDto newCard(@PathVariable String login,
	                       @RequestBody NewCardDto newCardDto) {
		return new CardDto();
	}

	// TODO: 27.04.2020 в разработке
	@PutMapping(name = "/{cardId}", params = "op=replenish")
	public CardDto replenishCard(@PathVariable String login,
	                             @PathVariable String cardId,
	                             @RequestBody ReplenishCardDto replenishCardDto) {
		// TODO: 27.04.2020 валидация пользователя
		return cardService.replenish(replenishCardDto, Long.parseLong(cardId));
	}

	// TODO: 27.04.2020 в разработке
	@PutMapping(name = "/{cardId}", params = "op=transfer")
	public CardDto transfer(@PathVariable String login,
							@PathVariable String cardId,
							@RequestBody TransferDto transferDto) {
		return (new CardDto());
	}

	// TODO: 27.04.2020 в разработке
	//возможно еще будет добавлен параметр для проверки токена, но это не точно
	//я даже не уверен что знаю что такое токен))
	@GetMapping(name = "/{cardId}", params = "op=view")
	public BalanceDto viewBalanceCard(@PathVariable String cardId) {
		return cardService.viewBalance(Long.parseLong(cardId));
	}
}
