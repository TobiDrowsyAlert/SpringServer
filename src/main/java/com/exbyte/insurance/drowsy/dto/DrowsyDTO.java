package com.exbyte.insurance.drowsy.dto;

import com.exbyte.insurance.drowsy.domain.PersonalVO;
import com.exbyte.insurance.drowsy.domain.StatusCode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DrowsyDTO {

	PersonalVO personalVO;
	StatusCode statusCode;
	
}
