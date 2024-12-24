package com.technivaaran.coe.exception;

public class CoeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CoeException(String errorMessage) {
		super(errorMessage);
	}
}
