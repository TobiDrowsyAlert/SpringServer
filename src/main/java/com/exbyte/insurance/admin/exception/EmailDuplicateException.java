package com.exbyte.insurance.admin.exception;

import lombok.Getter;

@Getter
public class EmailDuplicateException extends RuntimeException{
	
	private String email;
	
	public EmailDuplicateException(String email) {
		this.email = email;
	}
	
	
}
