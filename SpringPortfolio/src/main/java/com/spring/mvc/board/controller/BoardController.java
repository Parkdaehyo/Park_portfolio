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

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Inject
	private IBoardService service;
	
	@Inject
	private IUserService user_service;

	@GetMapping("/list")
	public String list(SearchVO search, Model model) {
		
		String condition = search.getCondition();
		
		System.out.println("URL: /board/list GET -> result: ");
		System.out.println("parameter(페이지번호) : " + search.getPage() +"번");
		System.out.println("검색조건: " + condition);
		System.out.println("검색어: " + search.getKeyword());
		
		
		PageCreator pc = new PageCreator(); //PageVO의 객체와, 페이징 알고리즘을 실행하는 로직의 객체를 생성
		pc.setPaging(search);  //page정보와 countPerPage의 정보 셋팅.
		
		List<BoardVO> list = service.getArticleList(search);
		pc.setArticleTotalCount(service.countArticles(search));
		
		model.addAttribute("articles" , list); //model로 list.jsp에 화면 전송
		model.addAttribute("pc" , pc); //PageVO의 객체와 알고리즘등을 기록한 로직들을 list.jsp에 전송
		return "board/list";
		
	
	}
	//게시글 작성페이지 요청
	@GetMapping("/write")
	public String write(HttpSession session, RedirectAttributes ra) {
		System.out.println("URL: /board/write => GET");
		
		//�꽭�뀡�쓣 遺덈윭���꽌, 洹� 濡쒓렇�씤 媛믪씠 null �씠�씪硫�,
		//not login 硫붿꽭吏�瑜� 蹂대깂. home.jsp
		
		//�씤�꽣�뀎�꽣濡� 泥섎━�븷寃껋씠湲� �븣臾몄뿉 二쇱꽍泥섎━ �븯寃좊떎.
		
		//if(session.getAttribute("login") == null) {
			//ra.addFlashAttribute("msg" , "not-login");
			
			//return "redirect:/"; //洹몃━怨� home.jsp濡� 蹂대궦�떎.		
		//}
		
		return "board/write";
	
	}
	
	//寃뚯떆湲� DB�벑濡� �슂泥� --> 寃뚯떆�뙋 �벑濡앸쾭�듉 �닃���쓣�븣 , �옄湲� �옄�떊(write.jsp)�븳�뀒 form�쓽 �궡�슜�뱾�쓣 蹂대궡怨�
	@PostMapping("/write") //�옄湲� �옄�떊�쓣 �뿴�븣 �뙆�씪誘명꽣 媛믪쓣 BoardVO�뿉 ���엯�빀�땲�떎.
	public String write(BoardVO article, RedirectAttributes ra) { //write.jsp�쓽 �뙆�씪誘명꽣媛� writer,title,content瑜� �떞�븘�꽌
		
		System.out.println("write �룷�뒪�듃 �옉�룞!!");
		System.out.println("Controller parameter: " + article);
	
	
	    service.insert(article);
		
		ra.addFlashAttribute("msg" , "regSuccess");
		
		return "redirect:/board/list"; //list瑜� �뿬�뒗 諛⑹떇. �씠�젃寃� �빐�빞 �뜲�씠�꽣踰좎씠�뒪�뿉 �뿰寃곗씠 �릺�꽌 媛��뒫�븳媛�蹂대떎.	
	}
	
	
	//게시글 상세 조회 요청
	@GetMapping("/content/{boardNo}")
	public String content(@PathVariable Integer boardNo, HttpSession session, Model model
			,@ModelAttribute("p") SearchVO paging) { // "p"媛� list.jsp "pc"�쓽 page�� countPerPage瑜� �씫�뼱�뱾�엫 //content�뿉�꽌 癒쇱� boardNo瑜� 諛쏆븘�빞�븳�떎.
		System.out.println("URL: /board/content => GET"); //�씠寃� �븞�쑍�떎�뒗嫄� handlerMapping�씠 紐살갼�븯�떎�뒗 �쑜.
		System.out.println("parameter(湲�踰덊샇): " + boardNo);
		UserVO user = (UserVO)session.getAttribute("login");
		BoardVO vo = service.getArticle(boardNo); //getArticle�씠 Integer boardNo瑜� 諛쏆쑝�젮硫� // BoardVO getArticle(Integer boardNo); BoardVO���엯�쓣 諛쏅뒗�떎.
		//getArticle�씠 BoardVO���엯�쓣 諛쏅뒗 硫붿꽌�뱶�씪�꽌 諛붾줈 boardNo瑜� 諛쏄쾶�빐�룄�겕寃� �긽愿� �뾾湲� �븳�뜲, 洹몃깷 vo�뿉 �꽔��寃�.
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo); //content.jsp�뿉 getArticle硫붿꽌�뱶�쓽 �뜲�씠�꽣瑜� 蹂대깂.
		model.addAttribute("login" , user);
		return "board/content";
	}
	
	//寃뚯떆湲� �궘�젣 �슂泥�
	@PostMapping("/delete")
	public String remove(Integer boardNo, PageVO paging, RedirectAttributes ra) {
		
		System.out.println("URL: /board/delete => POST");
		System.out.println("parameter(湲� 踰덊샇): " + boardNo);
		service.delete(boardNo);
		ra.addFlashAttribute("msg", "delSuccess") //�쟾�떖�븳 媛믪� url�뿉 遺숈� �븡�뒗�떎. �씪�쉶�꽦�씠�씪, 由ы봽�젅�떆 �븷 寃쎌슦 �뜲�씠�꽣媛� �냼硫몃맂�떎. �삉�븳, 2媛� �씠�긽 �벝 寃쎌슦 �뜲�씠�꽣�뒗 �냼硫명븳�떎. �뵲�씪�꽌 留듭쓣 �씠�슜�븯�뿬 �븳踰덉뿉 媛믪쓣 �쟾�떖�빐�빞�븳�떎.
		  .addAttribute("page" , paging.getPage()) //addAttribute: �쟾�떖�븳 媛믪� url �뮘�뿉 遺숈쑝硫�, 由ы봽�젅�떆�빐�룄 �뜲�씠�꽣媛� �쑀吏��맗�땲�떎.
		  .addAttribute("countPerPage" , paging.getCountPerPage());
		
		return "redirect:/board/list";
		//return "redirect:/board/list?page=" + paging.getPage() 諛⑸쾿1
		//+"&countPerPage=" + paging.getCountPerPage(); //�궘�젣 怨쇱젙�씠 �셿猷� �맂 �썑�쓽 list瑜� �옱�슂泥� �빐以섎씪.
	}
	
	
	
	//寃뚯떆湲� �닔�젙 �럹�씠吏� �슂泥� // �닔�젙�쓣 �븯湲곗쐞�빐�꽌�뒗 �닔�젙�쟾 �젙蹂대�� 遺덈윭���빞�븳�떎.
	@GetMapping("/modify")
	public String modify(Integer boardNo, Model model
			, @ModelAttribute("p") PageVO paging) {

		System.out.println("URL: /board/content => GET"); 
		System.out.println("parameter(湲�踰덊샇): " + boardNo);
		
		BoardVO vo = service.getArticle(boardNo);
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo); 
		
		return "board/modify";
	}
	
	//寃뚯떆湲� �닔�젙 �슂泥� // 沅곴레�쟻�쑝濡� write.jsp 諛⑹떇�븯怨� 留ㅼ슦 鍮꾩듂�븿.
	@PostMapping("/modify")
	public String modify(BoardVO article, RedirectAttributes ra) { //�뙆�씪誘명꽣媛믪쓣 �깭�슦�떎.
		System.out.println("URL: /board/delete => POST");
		System.out.println("parameter(湲� 踰덊샇): " + article);
		service.update(article);
		ra.addFlashAttribute("msg" , "modSuccess");
		return "redirect:/board/content/" + article.getBoardNo();
	}
	
			//�쉶�썝�젙蹂� �뿴�엺 �슂泥�
			@GetMapping("/mypage")
			public String mypage(UserVO inputData, Model model,HttpSession session) {
				
				 
				System.out.println("mypage 吏꾩엯!");
				System.out.println("Parameter: " + inputData);
		
				UserVO user = (UserVO)session.getAttribute("login");
				System.out.println(user);
				
				
				model.addAttribute("userinfo" , user);
				return "board/mypage";
			}
			
	//�쉶�썝 �젙蹂� �닔�젙 �슂泥�
	@PostMapping("/mypage")
	public String memberUpdate(UserVO vo, RedirectAttributes ra, HttpSession session) {
		
		System.out.println("mypage Post �슂泥��씠�떎!");
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
