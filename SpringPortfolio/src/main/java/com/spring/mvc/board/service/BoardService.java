package com.spring.mvc.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.SearchVO;
import com.spring.mvc.user.model.UserVO;



@Service
public class BoardService implements IBoardService {

	
	@Inject
	private IBoardMapper mapper;
	
	@Override
	public void insert(BoardVO article) {
			
		System.out.println("Debug "  + article.toString());
		//System.out.println("Debug "  + mpRequest.getMultiFileMap().toString());
		
				 mapper.insert(article);
				 
				 
				 /*
				 List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(article, mpRequest); 
					int size = list.size();
					for(int i=0; i<size; i++){ 
						list.get(i); 
						mapper.insertFile(size);
					}*/
				 
				 
			}
	
	/*
	public void insertFile(Map<String, Object> map) throws Exception {
		
		mapper.insert("boardMapper.insertFile", map);
	}*/

	
	@Override
	public BoardVO getArticle(Integer boardNo) {
		mapper.updateViewCnt(boardNo);
		return mapper.getArticle(boardNo);
	}

	@Override
	public void update(BoardVO article) {
		mapper.update(article);
		
	}

	@Override
	public void delete(Integer boardNo) {
		mapper.delete(boardNo);
		
	}
	
	@Override
	public List<BoardVO> getArticleList(SearchVO search) {
		
		List<BoardVO> list = mapper.getArticleList(search);
		
		//1일 이내 신규글 new마크 처리 로직
		
		for(BoardVO article : list) { //게시글을 하나하나씩 가져와서 너이거 신규게시물이니? 판단하게 할것이다.
			//현재 시간 읽어오기
			long now = System.currentTimeMillis(); //밀리초로 읽어오기 15억... * 1000초
			//Date regDate = article.getRegDate();
			long regTime = article.getRegDate().getTime(); 
		
			if(now - regTime < 60 * 60 * 24 * 1 * 1000) {
				article.setNewMark(true);
			}
		
		}
		
		
		return list;
	}
	
	@Override
	public Integer countArticles(SearchVO search) {
		
		return mapper.countArticles(search);
	}



	
	/*
	@Override
	public List<BoardVO> getArticleList() {
		return mapper.getArticleList();
	}

	@Override
	public List<BoardVO> getArticleListPaging(PageVO paging) {
		//page = (page - 1) * 10;
		return mapper.getArticleListPaging(paging);
	}
	
	@Override //이거하나로 전체조회도하고, 페이징도 하고, 검색도 한다.?
	public List<BoardVO> getArticleListByTitle(SearchVO search) {
		
		return mapper.getArticleListByTitle(search);
	}
	
	@Override
	public Integer countArticlesByTitle(SearchVO search) {
		
		return mapper.countArticlesByTitle(search);
	}
	
	@Override
	public List<BoardVO> getArticleListByWriter(SearchVO search) {
	
		return mapper.getArticleListByWriter(search);
	}
	@Override
	public Integer countArticlesByWriter(SearchVO search) {
		
		return mapper.countArticlesByWriter(search);
	}
	
	
	@Override
	public Integer countArticles() {
		return mapper.countArticles();
	}
	*/
	
	

}
