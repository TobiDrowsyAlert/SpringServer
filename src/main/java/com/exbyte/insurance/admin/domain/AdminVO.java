package com.exbyte.insurance.admin.domain;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AdminVO {

	String adminId;
	String adminPw;
	String adminName;
	String adminEmail;
	String adminPhone;
	String adminCallNumber;
	String adminPosition;
	String sessionKey;
	Date adminJoinDate;
	Date adminLoginDate;
	int adminPoint;
	String adminAuthKey;

	@Builder
	public AdminVO(String adminId, String adminPw, String adminName, String adminEmail, String adminPhone
			,String sessionKey, String adminCallNumber, String adminPosition, int adminPoint, String adminAuthKey) {
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPhone = adminPhone;
		this.adminCallNumber = adminCallNumber;
		this.adminPosition = adminPosition;
		this.sessionKey = sessionKey;
		this.adminPoint = adminPoint;
		this.adminAuthKey = adminAuthKey;
	}
	
}
