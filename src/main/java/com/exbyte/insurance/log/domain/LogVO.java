package com.exbyte.insurance.log.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LogVO {

	int staticNo;
	int blink;
	String adminId;
	Date time;
	Date drivingTime;
	boolean feedback;
	float pitch;
	float roll;
	float yaw;
	String reason;
	
}
