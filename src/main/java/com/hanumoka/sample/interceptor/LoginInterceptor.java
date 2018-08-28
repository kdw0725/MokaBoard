package com.hanumoka.sample.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//Login 요청 처리용 인터셉터
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	private static final String LOGIN = "login";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("Login preHandle start...");
		
		HttpSession session = request.getSession();
		
		//기존에 로그인 되어있으면 그 로그인 세션을 초기화 한다.
		if(session.getAttribute(LOGIN) != null) {
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
		}//if
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		logger.info("Login postHandle start...");
		
		HttpSession session = request.getSession();
		
		//LoginPost에서 생성한 Model 데이터에서 로그인 정보를 추출한다.
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		if(userVO != null) {
			logger.info("new login success...");
			session.setAttribute(LOGIN, userVO);  //계정정보를 session에 저장한다.
			//response.sendRedirect("/sample");
			
			//자동 로그인을 위한 쿠키 생성
			if(request.getParameter("useCookie") != null) {
				logger.info("쿠키 저장할 거임");
				
				logger.info("useCookie 체크박스 값:" + request.getParameter("useCookie").toString());
				
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				//세션 쿠키의 경우 브라우저를 종료하면 사라지지만
				//loginCookie의 경우 오랜 시간 보관하기 위해서 setMaxAge()를 이용하여 일주일간 브라우저에서 보관한다.
				loginCookie.setMaxAge(60 * 60 * 24 * 7);  
				response.addCookie(loginCookie);
			}else {
				logger.info("쿠키 저장 안할거임");
				//logger.info(request.getParameter("loginCookie").toString());
			}
			
			
			Object dest = session.getAttribute("dest");
			System.out.println("dest : " + (String)dest);
			session.removeAttribute("dest");  // 삭제를 안해주니... 계속 처음 로그인할때 접근한 페이지로 이동한다.
			response.sendRedirect(dest != null ? (String)dest : "/sample/home");
			
		}else {
			logger.info("new login fail...");
		}//else
		
	}
	
	
	
}
