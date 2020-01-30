package com.exbyte.insurance.consulting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ConsultingModifyDTO {
	int consultingNo;
	Boolean consultingIsCall;
	Boolean consultingIsEnd;
	String consultingRemarks;
}


