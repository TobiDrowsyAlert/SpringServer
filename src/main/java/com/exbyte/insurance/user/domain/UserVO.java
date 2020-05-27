package com.exbyte.insurance.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

	private String userId;
	private String userPassword;
	private String userName;
	private String userEmail;
	
}
