package com.spring.mvc.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	//회원가입 요청 처리
	//Rest-api에서 Insert 기능은 -> POST 이다.

	@PostMapping("/") // /은 그냥 /user와 같음.
	public String register(@RequestBody UserVO user) { //클라이언트가 회원가입 정보를 보내줄것이다.

		System.out.println("/user/ POST 요청 발생!");
		System.out.println("param: " + user);
		service.register(user);

		return "joinSuccess"; //joinSuccess.jsp를 열람하라는 뜻이 아니라, 클라이언트에게 이 메세지를 주는것이다.
	}

	
	
//	@GetMapping("/Findid")
//	public String Findid(UserVO user) {
//		
//		System.out.println("Findid 진입!");
//		
//		return ("user/login_modal");
//	}
	

	//아이디 찾기 요청 처리
	////////////////////////////// Refactoring //////////////////////////////
	@PostMapping("/Findid") 
	public UserVO FindId(@RequestBody UserVO inputData){ //inputData: 클라이언트에서 쓰는 정보 (보내는 정보)

		System.out.println("Null일 경우 \"\"(Null String)"+"\n name : "+
				inputData.getName()+", phone : "+inputData.getPhoneNum());    // 디버깅 출력문
		
		UserVO inputData2 = null;
		if(!inputData.getName().equals("") && inputData.getPhoneNum() != null) {    // name, phone이 빈값이 아닐 경우
			inputData2 =  service.findid(inputData);
		} 
		
		if(!(inputData2 instanceof UserVO)) {    // Service return 객체가 UserVO일 경우
			inputData2 = inputData;
		}
		
		//string은 equals비교라고? --> A. String비교는 equals :: String <-> String
		
		return inputData2;
		
	}
	///////////////////////////////////////////////////////////////////////////
	
	//아이디 중복확인 요청 처리
	@PostMapping("/checkId")
	public String checkId(@RequestBody String account) { 
		
		System.out.println("/user/checkId: Post요청 발생!");
		System.out.println("parameter: " + account);
		String result = null;
		
		Integer checkNum = service.checkId(account);
		if(checkNum == 1) { //SQL문 select count(*)에 의해서, count개수가 1이면 중복
			System.out.println("아이디가 중복됨!");
			result = "NO";
		} else {
			System.out.println("아이디 사용가능!");
			result = "OK";
		}
		
		return result;
	}
	
		//로그인 요청 처리
		@PostMapping("/loginCheck") //아이디 비번이 넘어가니까 POST요청
		public String loginCheck(@RequestBody UserVO inputData,
									/*HttpServletRequest request*/ 
					HttpSession session, 
					HttpServletResponse response) {
			
			//서버에서 세션객체를 얻는 방법
			//1. HttpServletRequest객체 사용
			//HttpSession session = request.getSession();
			//2. HttpSession 사용 - 두번째 방법.
			
			String result = null;
			
			/*
			 # 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를
			   조회해서 불러온다음 값 비교를 통해
			   1. 아이디가 없을 경우 클라이언트측으로 문자열 "idFail" 전송
			   2. 비밀번호가 틀렸을 경우 문자열 "pwFail"전송
			   3. 로그인 성공시 문자열 "loginSuccess" 전송
			 */
			System.out.println("/user/loginCheck 요청! : POST");
			System.out.println("Parameter: " + inputData);
			
			//DB에서 데이터를 가져옴
			//회원가입된 아이디라면 정상적으로 dbData에 값이 있을 것이고,
			//없다면, null값이 리턴 될것이다.
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			//selectOne 메서드(DB데이터)로 가져온 계정명의 정보를 UserVO dbData에 저장.
			UserVO dbData = service.selectOne(inputData.getAccount());
			
			if(dbData != null) {
				//입력된 Password와 DB에 존재하는 Password가 같다면
				//UserVO의 패스워드 입력과 DB의 데이터가 일치한다면,
				if(encoder.matches(inputData.getPassword(), dbData.getPassword())){
					session.setAttribute("login", dbData); //UserVO dbData를 "login"이라는 이름의 세션 객체를 설정.
					result = "loginSuccess"; //loginSuccess값 리턴
					
					long limitTime = 60 * 60 * 24 * 90; //3개월
					
					//자동로그인 체크시 처리
					//isAutoLogin()은 UserVO autoLogin의 getter값.
					if(inputData.isAutoLogin()) { //autologine이 true라면, 
						 System.out.println("자동 로그인 쿠키 생성중...");
						 //session.getId(): 브라우저에 있는 현재 session아이디를 불러올 수 있다. 이것을 "loginCookie"에 저장.
						 Cookie loginCookie = new Cookie("loginCookie" , session.getId()); //쿠키생성
						 loginCookie.setPath("/"); //loginCookie의 저장경로를 시작 URL로 설정
						 loginCookie.setMaxAge((int)limitTime); //setMaxAge는 int로 매개를 받음.
						
						 response.addCookie(loginCookie); //쿠키를 클라이언트에 실어보낼때, response객체 사용.
						 //이것을 사용하기위해서 매개변수로 HttpServletResponse response 사용.
						 
						 //자동 로그인 유지시간을 날짜 객체로 변환
						 //현재시간+3개월이 지난 시간(밀리초로 바꾸기위해 *1000을 곱한다?)
						 
						 /*
						  * Date limitDate = new Date(); 현재 시간이 들어오는 Date객체
						  * System.currentTimeMillis() : 현재시간 +
						  * limitTime = 105번 라인 3개월 시간, * 1000초.
						  * 이렇게  하면 현재 시간에서 3개월이 지난 시점의 밀리초를 
						  * Date객체에 넣음으로써 날짜로 포장이된다.
						  * 
						  */
						 
						 long expiredDate = System.currentTimeMillis() + (limitTime * 1000);
						 Date limitDate = new Date(expiredDate); //그 밀리초단위를 객체로 만들어서 날짜로 바꿔서 저장?
						 
						 service.keepLogin(session.getId(), limitDate, inputData.getAccount());
					}
					
				} else {
					result = "pwFail";
				}
			} else {
				result = "idFail";
			}
			
			return result;
		}
	
	//로그아웃 요청 처리
	@GetMapping("/logout") //home.jsp의 /user/logout을 누르는 처리.
	public ModelAndView logout(HttpSession session,
							   HttpServletRequest request,
							   HttpServletResponse response) {
		
		System.out.println("/user/logout 요청!");
		//로그인 세션을 가져와서 UserVO에 담겠다.
		UserVO user = (UserVO)session.getAttribute("login");
		
		//로그인 상태라면, 
		if(user != null) {
			session.removeAttribute("login"); //세션 객체에 저장된 속성을 제거한다.
			session.invalidate(); // 세션 객체의 모든 정보를 삭제한다.
			
			//로그아웃 시 자동로그인 쿠키 삭제 및 해당 회원 정보에서 session_id제거
			/*
			 * 1. loginCookie를 읽어온 뒤 해당 쿠키가 존재하는지 여부 확인
			 * 2. 쿠키가 존재한다면 쿠키의 수명을 0초로 다시 설정한 후 (setMaxAge)사용
			   3. 응답객체를 통해 로컬에 0초짜리 쿠키 재전송 -> 쿠키삭제
			   4. service를 통해 keepLogin을 호출하여 DB칼럼 레코드 재설정
			   (session_id -> "none" , limit_time -> 현재 시간으로)
			   
			   --> 로그아웃 시키는법:
			   쿠키를 읽어온다. -> 수명을 0초로 설정한다. (재설정) -> 응답객체(response)로 쿠키를 다시 보낸다. (재전송)
			   
			 */
			 
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // 1. 로그인 쿠키를 읽어온다.
			if(loginCookie != null) { // loginCookie 가 null값이 아니라는것은, 자동로그인을 한사람.
				loginCookie.setMaxAge(0); //로그인 쿠키의 수명을 0초로 설정
				response.addCookie(loginCookie); //그 후 addCookie()메서드로 다시 재전송함.
				//session아이디를 none으로 설정, new Date(): 현재시간으로 설정.
				service.keepLogin("none", new Date(), user.getAccount()); 
			}
		}
		
		
		return new ModelAndView("redirect:/");
	}
	
	
	
		
	//회원탈퇴 요청 처리
	//@RequestMapping(value="/" , method=RequestMethod.DELETE)
	//위줄을 @DeleteMapping으로 한줄 처리 가능.
	
	@DeleteMapping("/{account}") //아이디를 url로 받아서 삭제하는 방법.
	public String delete(HttpSession session, @PathVariable String account) {
		System.out.println("/user/" + account + " : DELETE 요청 발생!");

		UserVO user = (UserVO)session.getAttribute("login");
		
		if(user != null) {
			session.removeAttribute("login"); //세션 객체에 저장된 속성을 제거한다.
			session.invalidate(); 
		
		}
		
		service.delete(account);
		System.out.println("계정 아이디 삭제해버렸다.:" + account);
		
		
		return "delSuccess";
	}
	
	//회원정보 요청 처리
	@GetMapping("/{account}")
	public UserVO selectOne(@PathVariable String account) {
		
		System.out.println("/user/" + account + ": GET 요청발생!");
		
		return service.selectOne(account);
	}
	
	//회원정보 전체처리 요청 처리 // 관리자입장에서 쓸 가능성이 높음
	@GetMapping("/")
	public List<UserVO> selectAll() {
		
		System.out.println("/user/: GET 요청 발생!");
		
		return service.selectAll();
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "user/login_modal";
	}

}
