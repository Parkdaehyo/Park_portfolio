package com.spring.mvc.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터 클래스를 만드려면 HandlerInterceptorAdapter 클래스를 상속
public class TestInterceptor extends HandlerInterceptorAdapter {

	
	//preHandle은 특정 컨트롤러에 진입하기 전에 공통처리할 내용을 작성.
	@Override //pre + ctrl enter 오버라이딩 - 매개값의 형태는 같아야함(매개값을 함부로 수정할 수 없음)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		System.out.println("테스트 인터셉터의 preHandle이 작동!!");
		
		
		return true; //true가 리턴되면 컨트롤러로 진입, false가 리턴되면 진입 실패
	}
	
	//PostHandle은 컨트롤러를 나갈때 공통처리 해야할 내용을 작성한다. post 쓰고 자동완성
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("테스트 인터셉터의 postHandle 작동!!");
		
		//InterceptorController Model에 담겨있는 EL data를 가져옴.
		Object data = modelAndView.getModel().get("data");
		
		if(data != null) {
			//매개값에 session을 담을 수 없으므로, requst를 통해서 세션을 담겠다.
			request.getSession().setAttribute("data", data); 
			response.sendRedirect("/test1"); 
			//response.sendRedirect: 특정 상황에서 다른 페이지로 넘겨주고 싶을때 사용한다.
			
		}
	}
	
	
	
}
