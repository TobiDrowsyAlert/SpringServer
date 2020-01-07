package com.exbyte.insurance.commons.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int page;
	private int perPageNum;
	private String searchType;
	private String keyword;
	
	public Criteria() {
		page = 1;
		perPageNum = 10;
	}
	
	public void setPage(int page) {
		if(page <= 1) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum < 10 || perPageNum > 100) {
			perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	
	
}
