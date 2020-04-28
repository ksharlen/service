package ru.sberbank.service.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sberbank.service.dto.*;
import ru.sberbank.service.service.CardServiceImpl;

@RestController
@RequestMapping//("/{login}/cards")
public class CardController {
//	@Autowired
	private final CardServiceImpl cardService;

	public CardController(CardServiceImpl cardService) {
		this.cardService = cardService;
	}

	// TODO: 27.04.2020 в разработке
	@PostMapping(value = "/{login}/cards/new/")
	public CardDto newCard(@PathVariable String login,
	                       @RequestBody NewCardDto newCardDto) {
		// TODO: 28.04.2020 тут будет сервис валидации
		return cardService.addNewCard(newCardDto, login);
	}

	// TODO: 27.04.2020 в разработке
	@PutMapping(value = "/{login}/{cardId}/", params = "op=replenish")
	public CardDto replenishCard(@PathVariable String login,
	                             @PathVariable Long cardId,
	                             @RequestBody ReplenishCardDto replenishCardDto) {
		// TODO: 27.04.2020 валидация пользователя
		return cardService.replenish(replenishCardDto, cardId);
	}

	// TODO: 27.04.2020 в разработке
	@PutMapping(value = "/{login}/cards/{cardId}", params = "op=transfer")
	public CardDto transfer(@PathVariable(name = "login") String login,
							@PathVariable(name = "cardId") Long cardId,
							@RequestBody TransferDto transferDto) {
		return (new CardDto());
	}

	// TODO: 27.04.2020 в разработке
	//возможно еще будет добавлен параметр для проверки токена, но это не точно
	//я даже не уверен что знаю что такое токен))
	@GetMapping(value = "/{login}/{cardId}", params = "op=view")
	public BalanceDto viewBalanceCard(@PathVariable String login, @PathVariable(name = "cardId") Long cardId) {
		return cardService.viewBalance(cardId);
	}
}
