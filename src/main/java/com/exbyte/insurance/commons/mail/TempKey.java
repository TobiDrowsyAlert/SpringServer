package com.exbyte.insurance.commons.mail;

import java.util.Random;

public class TempKey {


	private boolean lowerCheck;
	private int size;
	
	public String getKey(int size, boolean lowerCheck) {
		this.size = size;
		this.lowerCheck = lowerCheck;
		return init();
	}
	
	// 난수 생성기
	private String init() {
		
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		
		do {
			// 숫자, 알파벳만 나오도록 처리
			num = ran.nextInt(75)+48;
			if((num >= 48 && num <=57) || (num >=65 && num <= 90) || (num >= 97 && num <=122)) {
				sb.append((char)num);
			}else {
				continue;
			}
		} while (sb.length() < size);
		// 문자열을 소문자로 전환
		if(lowerCheck) {
			return sb.toString().toLowerCase();
		}
		
		return sb.toString();
	}
	


}
