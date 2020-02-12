package com.exbyte.insurance.admin.exception;

public class InvalidAuthKeyAccessException extends RuntimeException {
	
	private String msg;

	public InvalidAuthKeyAccessException() {
		this.msg = "이메일을 확인해주세요.";
	}
	
	public InvalidAuthKeyAccessException(String msg) {
		this.msg = msg;
	}
}
