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
		// TODO Auto-generated constructor stub
	}

	public DoctorNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DoctorNotExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DoctorNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DoctorNotExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
