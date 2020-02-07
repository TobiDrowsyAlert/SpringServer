package com.exbyte.insurance.admin.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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

	
}
