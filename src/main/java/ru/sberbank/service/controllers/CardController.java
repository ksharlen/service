package ru.sberbank.service.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sberbank.service.dto.CardDto;
import ru.sberbank.service.dto.NewCardDto;
import ru.sberbank.service.dto.ReplenishCardDto;
import ru.sberbank.service.dto.TransferDto;

@RestController
@RequestMapping("/{login}/cards")
public class CardController {

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
		return new CardDto();
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
	public CardDto getInfoCard() {
		return new CardDto();
	}
}
