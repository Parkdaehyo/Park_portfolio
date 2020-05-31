package com.spring.mvc.commons;

public class PageVO {
	
	private Integer page;//사용자가 요청한 페이지 번호
	private Integer countPerPage;//한 페이지당 들어갈 게시물의 수
	
	
	/*아까 왜 list.jsp에 접속했을때 nullpointerException이 떴을까? 
	  페이지번호를 주지 않았기 때문에 무슨 페이지를 열어야 할지 몰랐기 때문이다.
	 그래서 처음 list.jsp에 접속할때 1페이지가 뜨게끔 설정하자.
	 그리고 10개씩 보이도록 설정하자.
	 
	*/
	
	
	public PageVO() {
		this.page = 1;
		this.countPerPage = 10;
	}
	
	//클라이언트가 전달한 페이지번호를 데이터베이스의 LIMIT절에 맞는 숫자로 변환.
	public Integer getPageStart() {
		return (this.page - 1) * this.countPerPage;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		
		if(page <= 0) {
			this.page = 1;
			return;
		}		
		this.page = page;
	}
	public Integer getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(Integer countPerPage) {
		if(countPerPage <= 0 || countPerPage > 50) {
			this.countPerPage = 10;
			return;
		}
		this.countPerPage = countPerPage;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", countPerPage=" + countPerPage + "]";
	}
	
	

}



