package com.exbyte.insurance.commons.exception;

public class EmailAuthException extends Exception{
	private final int ERR_CODE;
	
	public EmailAuthException(String msg) {
		super(msg);
		this.ERR_CODE = 100;
	}
	public EmailAuthException(String msg, int errorCode) {
		super(msg);
		ERR_CODE = errorCode;
	}

	public int getErrorCode() {
		return ERR_CODE;
	}
}
