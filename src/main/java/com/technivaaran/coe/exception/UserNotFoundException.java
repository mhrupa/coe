package com.technivaaran.coe.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
