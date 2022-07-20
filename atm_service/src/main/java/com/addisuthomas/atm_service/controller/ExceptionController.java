package com.addisuthomas.atm_service.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.addisuthomas.atm_service.exceptions.CustomExceptionResponse;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(customExceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRunTimeException(RuntimeException ex, WebRequest request) {
		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(customExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
