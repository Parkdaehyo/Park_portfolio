package com.spring.mvc.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;
import com.spring.mvc.commons.PageCreator;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;
import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

//깃허브 테스트 5월 8일 2차 수정
@Controller
@RequestMapping("/board")
public class BoardController {
	
	//Logger log = Logger.getLogger(this.getClass());

	@Inject
	private IBoardService service;
	
	@Inject
	private IUserService user_service;
	
	
	//게시글 목록 불러오기 요청
	/*@GetMapping("/list")
	public String list(Model model) {
		List<BoardVO> list = service.getArticleList();
		
		//System.out.println("URL: /board/list GET -> result: " + list.size());
		//list.forEach(article -> System.out.println(article));
		model.addAttribute("articles" , list); //model로 list.jsp에 화면 전송
		return "board/list";
	}*/
	
	/*
	//페이징 처리 이후 게시글 목록 불러오기 요청
	@GetMapping("/list")
	public String list(PageVO paging, Model model) {
		List<BoardVO> list = service.getArticleListPaging(paging);
		
		System.out.println("URL: /board/list GET -> result: " + list.size());
		System.out.println("parameter(페이지번호) : " + paging.getPage() +"번");
		
		PageCreator pc = new PageCreator(); //PageVO의 객체와, 페이징 알고리즘을 실행하는 로직의 객체를 생성
		pc.setPaging(paging); //page정보와 countPerPage의 정보 셋팅.
		pc.setArticleTotalCount(service.countArticles()); //총 게시물수를 DB에 가져오는 메서드
		System.out.println(pc);
		
		//list.forEach(article -> System.out.println(article));
		model.addAttribute("articles" , list); //model로 list.jsp에 화면 전송
		model.addAttribute("pc" , pc); //PageVO의 객체와 알고리즘등을 기록한 로직들을 list.jsp에 전송
		return "board/list";
	}
	*/
	
	//검색 처리 이후 게시글 목록 불러오기 요청
	@GetMapping("/list")
	public String list(SearchVO search, Model model) {
		
		String condition = search.getCondition();
		
		
		System.out.println("URL: /board/list GET -> result: ");
		System.out.println("parameter(페이지번호) : " + search.getPage() +"번");
		System.out.println("검색 조건: " + condition);
		System.out.println("검색어: " + search.getKeyword());
		
		
		PageCreator pc = new PageCreator(); //PageVO의 객체와, 페이징 알고리즘을 실행하는 로직의 객체를 생성
		pc.setPaging(search); //page정보와 countPerPage의 정보 셋팅.
		
		List<BoardVO> list = service.getArticleList(search);
		pc.setArticleTotalCount(service.countArticles(search));
		
		model.addAttribute("articles" , list); //model로 list.jsp에 화면 전송
		model.addAttribute("pc" , pc); //PageVO의 객체와 알고리즘등을 기록한 로직들을 list.jsp에 전송
		return "board/list";
		
		/*List<BoardVO> list = null;
		
		if(condition.equals("title")) { //list.jsp의 파라미터값?
			
			list = service.getArticleListByTitle(search); //제목으로 검색한 페이지를 보여줘라
			pc.setArticleTotalCount(service.countArticlesByTitle(search)); //제목으로 검색 한 이후 게시물 수 가져오기 + 페이징 알고리즘
		
		}else if(condition.equals("writer")) {
			list = service.getArticleListByWriter(search); //작성자로 검색한 페이지르
			pc.setArticleTotalCount(service.countArticlesByWriter(search)); //작성자로 검색 한 이후 게시물의 수를 가져오기 + 페이징 알고리즘
			
		}else {
			list = service.getArticleListPaging(search); //페이지 목록 조회기능
			pc.setArticleTotalCount(service.countArticles()); //총 게시물 수 조회기능
		}*/
	
		
		//list.forEach(article -> System.out.println(article));
		
	
	}
	//게시글 작성페이지 요청
	@GetMapping("/write")
	public String write(HttpSession session, RedirectAttributes ra) {
		System.out.println("URL: /board/write => GET");
		
		//세션을 불러와서, 그 로그인 값이 null 이라면,
		//not login 메세지를 보냄. home.jsp
		
		//인터셉터로 처리할것이기 때문에 주석처리 하겠다.
		
		//if(session.getAttribute("login") == null) {
			//ra.addFlashAttribute("msg" , "not-login");
			
			//return "redirect:/"; //그리고 home.jsp로 보낸다.		
		//}
		
		return "board/write";
	
	}
	
	//게시글 DB등록 요청 --> 게시판 등록버튼 눌렀을때 , 자기 자신(write.jsp)한테 form의 내용들을 보내고
	@PostMapping("/write") //자기 자신을 열때 파라미터 값을 BoardVO에 대입합니다.
	public String write(BoardVO article, RedirectAttributes ra) { //write.jsp의 파라미터값 writer,title,content를 담아서
		
		System.out.println("write 포스트 작동!!");
		System.out.println("Controller parameter: " + article);
	
	
	    service.insert(article);
		
		ra.addFlashAttribute("msg" , "regSuccess");
		
		return "redirect:/board/list"; //list를 여는 방식. 이렇게 해야 데이터베이스에 연결이 되서 가능한가보다.	
	}
	
	
	//게시글 상세 조회 요청
	@GetMapping("/content/{boardNo}")
	public String content(@PathVariable Integer boardNo, Model model
			,@ModelAttribute("p") SearchVO paging) { // "p"가 list.jsp "pc"의 page와 countPerPage를 읽어들임 //content에서 먼저 boardNo를 받아야한다.
		System.out.println("URL: /board/content => GET"); //이게 안뜬다는건 handlerMapping이 못찾았다는 뜻.
		System.out.println("parameter(글번호): " + boardNo);
		BoardVO vo = service.getArticle(boardNo); //getArticle이 Integer boardNo를 받으려면 // BoardVO getArticle(Integer boardNo); BoardVO타입을 받는다.
		//getArticle이 BoardVO타입을 받는 메서드라서 바로 boardNo를 받게해도크게 상관 없긴 한데, 그냥 vo에 넣은것.
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo); //content.jsp에 getArticle메서드의 데이터를 보냄.
		return "board/content";
	}
	
	//게시글 삭제 요청
	@PostMapping("/delete")
	public String remove(Integer boardNo, PageVO paging, RedirectAttributes ra) {
		
		System.out.println("URL: /board/delete => POST");
		System.out.println("parameter(글 번호): " + boardNo);
		service.delete(boardNo);
		ra.addFlashAttribute("msg", "delSuccess")
		  .addAttribute("page" , paging.getPage()) //ra생략가능
		  .addAttribute("countPerPage" , paging.getCountPerPage());
		
		return "redirect:/board/list";
		//return "redirect:/board/list?page=" + paging.getPage() 방법1
		//+"&countPerPage=" + paging.getCountPerPage(); //삭제 과정이 완료 된 후의 list를 재요청 해줘라.
	}
	
	
	
	//게시글 수정 페이지 요청 // 수정을 하기위해서는 수정전 정보를 불러와야한다.
	@GetMapping("/modify")
	public String modify(Integer boardNo, Model model
			, @ModelAttribute("p") PageVO paging) {

		System.out.println("URL: /board/content => GET"); 
		System.out.println("parameter(글번호): " + boardNo);
		
		BoardVO vo = service.getArticle(boardNo);
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo); 
		
		return "board/modify";
	}
	
	//게시글 수정 요청 // 궁극적으로 write.jsp 방식하고 매우 비슷함.
	@PostMapping("/modify")
	public String modify(BoardVO article, RedirectAttributes ra) { //파라미터값을 태우다.
		System.out.println("URL: /board/delete => POST");
		System.out.println("parameter(글 번호): " + article);
		service.update(article);
		ra.addFlashAttribute("msg" , "modSuccess");
		return "redirect:/board/content/" + article.getBoardNo();
	}
	
			//회원정보 열람 요청
			@GetMapping("/mypage")
			public String mypage(UserVO inputData, Model model,HttpSession session) {
				
				 
				System.out.println("mypage 진입!");
				System.out.println("Parameter: " + inputData);
		
				UserVO user = (UserVO)session.getAttribute("login");
				System.out.println(user);
				
				
				model.addAttribute("userinfo" , user);
				return "board/mypage";
			}
			
	//회원 정보 수정 요청
	@PostMapping("/mypage")
	public String memberUpdate(UserVO vo, RedirectAttributes ra, HttpSession session) {
		
		System.out.println("mypage Post 요청이다!");
		System.out.println(vo);
		user_service.memberupdate(vo);
		session.invalidate();
		ra.addFlashAttribute("msg" , "modSuccess");
		
		return "redirect:/";
		
	}
	
	@GetMapping("/album")
	public String musicvideo() {
		
		
		
		return "board/req_album";
	}
	
	
	
}
