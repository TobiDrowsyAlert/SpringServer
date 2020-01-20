package com.exbyte.insurance.consulting.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConsultingVO {

	int consultingNo;
	String adminId;
	String consultingName;
	String consultingEmail;
	String consultingSex;
	Date consultingBirthday;
	String consultingKinds;
	String consultingType;
	Boolean consultingIsCall;
	Boolean consultingIsEnd;
	Date consultingRegDate;
	String consultingFavoriteType;
	String consultingRegion;
	String consultingRemarks;
	
}
