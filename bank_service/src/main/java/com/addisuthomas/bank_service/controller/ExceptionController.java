package com.addisuthomas.bank_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleRunTimeException(RuntimeException ex) {
		return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
