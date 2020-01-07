package com.exbyte.insurance.admin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {

	private String adminId;
	private String adminPw;
	private boolean useCookie;
	
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	public boolean getUseCookie() {
		return useCookie;
	}
}
