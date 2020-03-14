package com.exbyte.insurance.drowsy.domain;

public enum StatusCode {

	CODE_000(000, "UnKnown"),
	CODE_100(100, "눈깜빡임"),
	CODE_101(101, "눈 감음"),
	CODE_200(200, "하품"),
	CODE_300(300, "운전자 이탈"),
	CODE_301(301, "운전자 인식 실패"),
	CODE_400(400, "정상");
	
	
	private final int code;
	private final String status;
	
	StatusCode(int code, String status){
		this.code = code;
		this.status = status;
	}
}
