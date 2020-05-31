package com.spring.mvc.commons;

public class SearchVO extends PageVO {
	
	//PageVO를 field로 만드는것도 괜찮지만 상속을했다?
	//단순히 페이징 기능만 필요하면 PageVO, 검색+페이징 = SearchVO 사용
	private String keyword; // 검색 키워드
	private String condition; //검색 조건
	
	public SearchVO() {
		this.keyword = "";
		this.condition = "";
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
}
