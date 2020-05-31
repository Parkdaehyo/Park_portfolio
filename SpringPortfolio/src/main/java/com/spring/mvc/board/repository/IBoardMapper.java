package com.spring.mvc.board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.commons.SearchVO;

//게시판 관련 CRUD 추상메서드 선언 
public interface IBoardMapper {

	//게시글 등록기능
	void insert(BoardVO article);
	//void insert(BoardVO aritcle);

	//게시글 단일(상세) 조회기능
	BoardVO getArticle(Integer boardNo); //Integer == int
	
	//게시물 조회수 상승처리
	void updateViewCnt(Integer boardNo);
	
	//게시글 수정기능
	void update(BoardVO article);

	//게시글 삭제 기능
	void delete(Integer boardNo);
	
	//public void insertFile1(Map<String, Object> map) throws Exception;
	
	
	//첨부파일 업로드
	//public void insertFile(List<Map<String, Object>> list) throws Exception;
	
	
	

	//////////////////////////////////////////////////////

	//# 검색, 페이지 기능이 포함된 게시글 목록 조회기능
	List<BoardVO> getArticleList(SearchVO search);
	
	Integer countArticles(SearchVO search);

	//public void insertFile(int size) throws Exception;

	

	//게시글 목록 조회 기능
	//List<BoardVO> getArticleList();

	//게시글 페이지 목록 조회 기능
	//List<BoardVO> getArticleListPaging(PageVO paging);

	//제목으로 검색기능
	//List<BoardVO> getArticleListByTitle(SearchVO search);

	//제목으로 검색 이후 게시물 수 조회기능
	//Integer countArticlesByTitle(SearchVO search);


	//작성자로 검색기능
	//List<BoardVO> getArticleListByWriter(SearchVO search);

	//작성자 검색 이후 게시물 수 조회기능
	//Integer countArticlesByWriter(SearchVO search);

	//총 게시물 수 조회기능
	//Integer countArticles(); //SQL문의  -- 총 게시물 수를 조회하는 쿼리
	//SELECT COUNT(*)
	//FROM new_board;


}