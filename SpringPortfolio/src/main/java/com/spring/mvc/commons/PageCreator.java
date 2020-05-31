package com.spring.mvc.commons;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//페이징 알고리즘을 처리하기 위한 객체 생성
public class PageCreator {

	//페이지번호와 한 페이지당 들어갈 게시물 수를 갖고 있는 객체
	private PageVO paging;
	private Integer articleTotalCount; //게시판의 총 게시물 수
	private Integer beginPage; //시작 페이지 번호
	private Integer endPage; // 끝 페이지 번호
	private boolean prev; // 이전 버튼 활성화 여부
	private boolean next; // 다음 버튼 활성화 여부
	
	//한 화면에 보여질 페이지 수
	private final Integer displayPageNum = 10;
	
	//URI 파라미터를 쉽게 만들어주는 유틸 메서드 선언.
	public String makeURI(Integer page) {
		UriComponents ucp = UriComponentsBuilder.newInstance()
							.queryParam("page", page) //queryParam: 파라미터를 넘겨줌.
							.queryParam("countPerPage", paging.getCountPerPage())
							.queryParam("keyword" , ((SearchVO)paging).getKeyword())
							.queryParam("condition", ((SearchVO)paging).getCondition())
							.build();
		
		return ucp.toUriString();
		
	}
	
	//페이징 알고리즘을 수행할 메서드 선언.
	private void calcDataOfPage() {
		
		endPage = (int)Math.ceil(paging.getPage() //ex) 현재 보고있는페이지가 9, 9/10 = 0.9 고 올리면 1.0 * 10 = 10페이지가 endPage
				/ (double)displayPageNum)
				* displayPageNum;
		
		//시작 페이지 번호 구하기
		beginPage = (endPage - displayPageNum) + 1;
		
		//현재 시작페이지가 1이라면 이전 버튼 활성화 여부를 false로 지정
		prev = (beginPage == 1) ? false : true; //prev는 beginPage가 1이 아니라면 true
		
		//마지막 페이지 인지 여부 확인 후 다음 버튼 비활성. 총 게시물수가 보정전 끝페이지 * 보여지는 게시물 수 보다 작으면 비활성화 하겠다.
		next = (articleTotalCount <= (endPage * paging.getCountPerPage())) ? false : true;
		
		//재보정 여부 판단하기
		if(!isNext()) { //현재 페이지가 31페이지라면, 보정전 끝페이지는 40이지만 이 구문에서 보정이 되서 33이됨.
			endPage = (int)Math.ceil(articleTotalCount / (double)paging.getCountPerPage());
		}
		
	}

	public PageVO getPaging() {
		return paging;
	}

	public void setPaging(PageVO paging) {
		this.paging = paging;
	}

	public Integer getArticleTotalCount() {
		return articleTotalCount;
	}

	//컨트롤러에서 게시물 수를 DB에서 받아오면 게시물수가 들어오면서 알고리즘을 실행하게한다.
	public void setArticleTotalCount(Integer articleTotalCount) {
		this.articleTotalCount = articleTotalCount;
		calcDataOfPage();
	}

	public Integer getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(Integer beginPage) {
		this.beginPage = beginPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() { //boolean 타입의 getter
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() { //boolean 타입의 getter
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Integer getDisplayPageNum() {
		return displayPageNum;
	}

	@Override
	public String toString() {
		return "PageCreator [paging=" + paging + ", articleTotalCount=" + articleTotalCount + ", beginPage=" + beginPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum
				+ "]";
	}
	
	
	
	
	
	
	
}
