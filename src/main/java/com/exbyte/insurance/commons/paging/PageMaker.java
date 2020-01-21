package com.exbyte.insurance.commons.paging;

import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageMaker {
	
	private int displayPageNum = 10;
	private int totalPageNum;
	private int startPage;
	private int endPage;
	private Criteria criteria;
	private Boolean prev, next;
	
	
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
		calcData();
	}

	public void calcData() {
		
		endPage = (int)Math.ceil(criteria.getPage()/(double)displayPageNum) * displayPageNum;
		startPage = endPage - displayPageNum + 1;
		
		int tempEndPage = (int)Math.ceil(totalPageNum / (double)criteria.getPerPageNum());

		if(tempEndPage < endPage) {
			endPage = tempEndPage;
		}
		
		prev = (startPage != 1);
		next = (endPage * criteria.getPerPageNum() < totalPageNum);
	}
	
	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", criteria.getPerPageNum())
				.queryParam("searchType", criteria.getSearchType())
				.queryParam("keyword", criteria.getKeyword())
				.queryParam("sortType", criteria.getSortType())
				.queryParam("sortOrder", criteria.getSortOrder())
				.build();
		
		return uriComponents.toString();
	}
	
    public String makeQuery(int page){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .build();

        return uriComponents.toString();
    }
    
    public String makeQuery(){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", criteria.getPage())
                .queryParam("perPageNum", criteria.getPerPageNum())
                .build();

        return uriComponents.toString();
    }
    
    public String encoding(String keyword){
        if(keyword == null || keyword.trim().length() == 0){
            return "";
        }
        try{
            return URLEncoder.encode(keyword, "UTF-8");
        }catch (Exception e){
            return "";
        }
    }
	
}
