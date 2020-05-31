package com.spring.mvc.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//게시판에 들어갈때 회원인지 특정경로에서 확인하겠다. 공통처리 하겠다.
public class BoardInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*
		BoardController에 요청 보낸 놈이 회원인지 아닌지 확인 그렇다면 Session을 받아와야한다.
		그런데 매개변수에 Session이 없다 어떻게 하지?
		오버라이딩 된것은 매개변수값에 추가할 수가 없다.(준 그대로 사용한다)
		이럴때 Session이 필요하다 
		request.getSession()으로 받아올 수 있다.
		HttpSession이 반환값이다.
		 */
		
		System.out.println("게시판 인터셉터 발동!");
		HttpSession session = request.getSession(); //request으로 세션을 가져와서 쓸 수 있도록 설정.
		
		//가져온 "login"이라는 session의 값이 null이라면
		if(session.getAttribute("login") == null) { //UserController에 있는 login session을 가져옴.
			System.out.println("회원 인증 실패!");
			
			//response.sendRedirect("/"); //홈으로보내기
			
			//자바 소스로 자바스크립트 쓰기? out 객체쓰기
			//이걸로 자바스크립트와 HTML쓰기가 가능?
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String element = "<script>"
					+ "alert('로그인을 해주세요!');"
					+ "location.href='/';" //response.sendRedirect 대용
					+ "</script>";
			out.print(element);
			out.flush();//브라우저 출력버퍼 비우기
			out.close();//out객체 반납하기.
			
			return false; //false값 반환
		}
		
		
		return true;
	}
	
}
