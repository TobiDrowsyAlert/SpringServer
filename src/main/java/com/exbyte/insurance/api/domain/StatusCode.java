package com.exbyte.insurance.api.domain;

public enum StatusCode {
    INT_BLINK(100, "눈 깜빡임"),
    INT_BLIND(101, "눈 감김"),
    INT_YAWN(200, "하품"),
    INT_DRIVER_AWAY(300, "운전자 이탈"),
    INT_DRIVER_AWARE_FAIL(301, "정면 주시 실패"),
    INT_NORMAL(400, "정상");

	
	StatusCode(int code, String reason) {
		this.code = code;
		this.reason = reason;
	}
	
	private final int code;
	private final String reason;
	
	
	static public String getReason(int code) {
		String reason = null;
		
		if(code == INT_BLIND.code) {
			reason = INT_BLIND.reason;
		}else if(code == INT_BLINK.code) {
			reason = INT_BLINK.reason;
		}else if(code == INT_YAWN.code) {
			reason = INT_YAWN.reason;
		}else if(code == INT_DRIVER_AWAY.code) {
			reason = INT_DRIVER_AWAY.reason;
		}else if(code == INT_DRIVER_AWARE_FAIL.code) {
			reason = INT_DRIVER_AWARE_FAIL.reason;
		}else if(code == INT_NORMAL.code) {
			reason = INT_NORMAL.reason;
		}
		
		return reason;
	}

}
