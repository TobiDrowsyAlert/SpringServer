package com.exbyte.insurance.api.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LogVO {	
	private int logNo; // not null
	private int blink;
	private String userId; // not null
	private int stage;
	private Date curTime;
	private Date drivingTime;
	private Boolean isCorrect;
	private double pitch; 
	private double roll;
	private double yaw;
	private double mar;
	private double ear;
	private String reason; // 졸음 이유
	
	public LogVO() {
		
	};
	
	public LogVO(ResponseDTO dto, String userId){
		logNo = 0;
		blink = dto.getBlink();
		this.userId = userId;
		this.stage = dto.getSleep_step();
		this.pitch = dto.getPitch();
		this.roll = dto.getRoll();
		this.yaw = dto.getYaw();
		this.mar = 0;
		this.ear = 0;
		this.reason = StatusCode.getReason(dto.getStatus_code()); //dto.getSleep_step();
		
	}

}