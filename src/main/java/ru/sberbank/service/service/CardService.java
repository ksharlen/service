package ru.sberbank.service.service;

import ru.sberbank.service.dto.*;

public interface CardService {
	CardDto replenish(ReplenishCardDto replenishCardDto, Long idCard);
	CardDto transfer(TransferDto transferDto, Long idCard);
	BalanceDto viewBalance(Long idCard);
	CardDto addNewCard(NewCardDto newCardDto, String login);
}
