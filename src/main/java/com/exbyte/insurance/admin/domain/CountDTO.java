package com.exbyte.insurance.admin.domain;

import lombok.Getter;

@Getter
public class CountDTO {
	private AdminVO admin;
	
	@Getter
	public enum SearchType {
		SESSION("sessionKey"),
		EMAIL("adminEmail"),
		ID("adminId");
		
		private final String type;
		
		SearchType(String type){
			this.type = type;
		}
	}
}
