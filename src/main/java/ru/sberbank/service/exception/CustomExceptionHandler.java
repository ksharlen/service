package ru.sberbank.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	private static final String RECORD_NOT_FOUND = "Запись не найденна";
	private static final String DUPLICATE_RECORD = "Пользователель с таким именем уже существует";

	@ExceptionHandler(DuplicateRecordException.class)
	public final ResponseEntity<ErrorResponse> handlerAllExceptions(DuplicateRecordException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(DUPLICATE_RECORD, details);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
}

class ErrorResponse {
	private String message;
	private List<String> details;

	public ErrorResponse(String message, List<String> details) {
		this.message = message;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
}
