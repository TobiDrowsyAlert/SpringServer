package com.exbyte.insurance.api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
	private int sleep_step;
	private int status_code;
	private int blink;
	private int yawn;
	private int pitch;
	private int yaw;
	private int roll;
}
