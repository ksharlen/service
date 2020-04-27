package ru.sberbank.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.service.service.CardServiceImpl;

@RestController
@RequestMapping("/add")
public class TestController {
	@Autowired
	private CardServiceImpl cardService;

	@GetMapping
	public void addCard() {

	}
}
