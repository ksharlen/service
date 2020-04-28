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
	// TODO: 28.04.2020 временная запись
	private static final String DUPLICATE_RECORD = "Пользователель с таким именем уже существует";
	private static final String BAD_REQUEST = "Неверный запрос";

	// TODO: 28.04.2020 временное решение
	// TODO: 28.04.2020 подумать над дублированием, надо убрать!
	@ExceptionHandler(DuplicateRecordException.class)
	public final ResponseEntity<ErrorResponse> handlerAllExceptions(DuplicateRecordException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(DUPLICATE_RECORD, details);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(RECORD_NOT_FOUND, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ErrorResponse> handlerBadRequest(BadRequestException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}

// TODO: 28.04.2020 временное явление
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
