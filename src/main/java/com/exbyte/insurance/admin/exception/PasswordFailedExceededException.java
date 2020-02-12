package com.exbyte.insurance.admin.exception;

public class PasswordFailedExceededException extends RuntimeException {

	private String msg;
	
	public PasswordFailedExceededException(String msg) {
		this.msg = msg;
	}
}
