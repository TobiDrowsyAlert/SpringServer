package com.exbyte.insurance.admin.exception;

import lombok.Getter;

@Getter
public class AdminNotFoundException extends RuntimeException{
	
	private String id;
	private String email;
	
	public AdminNotFoundException(String id) {
		this.id = id;
	}
	

}
