package com.example.demo.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ChatNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3659725605758492405L;

	public ChatNotExistException() {
		super();
	}

	public ChatNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChatNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChatNotExistException(String message) {
		super(message);
	}

	public ChatNotExistException(Throwable cause) {
		super(cause);
	}

}
