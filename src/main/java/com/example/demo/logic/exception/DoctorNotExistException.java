package com.example.demo.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DoctorNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4674094688356807706L;

	public DoctorNotExistException() {
		super();
	}

	public DoctorNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DoctorNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoctorNotExistException(String message) {
		super(message);
	}

	public DoctorNotExistException(Throwable cause) {
		super(cause);
	}

}
