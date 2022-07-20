package com.addisuthomas.atm_service.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomExceptionResponse extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Date timeStamp;
	private String status;
	private String message;

	public CustomExceptionResponse(Date timeStamp, String status, String message) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
