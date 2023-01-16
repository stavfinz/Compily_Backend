package com.example.demo.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InValidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6180088311815411164L;

	public InValidInputException() {
		super();
	}

	public InValidInputException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InValidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InValidInputException(String message) {
		super(message);
	}

	public InValidInputException(Throwable cause) {
		super(cause);
	}

}
