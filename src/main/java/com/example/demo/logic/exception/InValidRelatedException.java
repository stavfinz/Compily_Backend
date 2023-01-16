package com.example.demo.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class InValidRelatedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidRelatedException() {
		super();
	}

	public InValidRelatedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InValidRelatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public InValidRelatedException(String message) {
		super(message);
	}

	public InValidRelatedException(Throwable cause) {
		super(cause);
	}

}
