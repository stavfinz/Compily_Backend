package com.example.demo.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TreatmentNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2840992983195997661L;

	public TreatmentNotExistException() {
		super();
	}

	public TreatmentNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TreatmentNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public TreatmentNotExistException(String message) {
		super(message);
	}

	public TreatmentNotExistException(Throwable cause) {
		super(cause);
	}

}
