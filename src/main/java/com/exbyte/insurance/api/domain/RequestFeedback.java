package com.exbyte.insurance.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class RequestFeedback {

	private int logNo;
	private Boolean isCorrect;
	private Boolean isFeedback;
	private String curTime;
	
	public Boolean getIsCorrect() {
		return isCorrect;
		
	}
}
