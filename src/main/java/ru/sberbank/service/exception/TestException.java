package ru.sberbank.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "this is Throwable")
public class TestException extends RuntimeException {
	public TestException() {}

	public TestException(String msg) {
		super(msg);
	}
}
