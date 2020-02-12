package com.exbyte.insurance.admin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginDTO {

	private String adminId;
	private String adminPw;
	private int adminPoint;
	private boolean useCookie;
	
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	public boolean getUseCookie() {
		return useCookie;
	}
	
	@Builder
	public LoginDTO(String adminId, String adminPw, int adminPoint, boolean useCookie) {
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.adminPoint = adminPoint;
		this.useCookie = useCookie;
	}
	
}
