package ru.sberbank.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRecordException extends RuntimeException {
	public DuplicateRecordException(String msg) {
		super(msg);
	}
}
