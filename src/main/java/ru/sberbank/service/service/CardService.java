package ru.sberbank.service.service;

import ru.sberbank.service.dto.CardDto;
import ru.sberbank.service.dto.ReplenishCardDto;
import ru.sberbank.service.dto.TransferDto;

public interface CardService {
	CardDto replenish(ReplenishCardDto replenishCardDto, Long idCard);
	CardDto transfer(TransferDto transferDto, Long idCard);
	CardDto viewBalance(Long idCard);
}
