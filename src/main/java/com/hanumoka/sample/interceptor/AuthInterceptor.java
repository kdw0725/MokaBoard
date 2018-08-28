package com.hanumoka.sample.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.hanumoka.sample.service.UserService;
import com.hanumoka.sample.vo.UserVO;

//특정 경로에 접근하는 경우 현재 사용자가 로그인 상태의 사용자인지 체크하는 역할을 처리하는 인터셉터
public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Inject
	private UserService service;

	//로그인 성공후 원래 URI 로 이동시키는 작업을 위한 전처리
	private void saveDest(HttpServletRequest req) {
		
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}// else
		
		if(req.getMethod().equals("GET")) {
			logger.info("dest:" + (uri + query));
			//원래 사용자가 원하는 페이지의 정보를 httpSession에 dest에 저장한다.GET 방식인 경우에는 URI 정보 뒤으 파라미터들을 함께 보관한다.
			req.getSession().setAttribute("dest", uri + query);
		}//if
		
	}//saveDest
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			logger.info("current user is not logined");
			
			saveDest(request);
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				UserVO userVO = service.checkLoginBefore(loginCookie.getValue());
				logger.info("USERVO:"+userVO);
				
				if(userVO != null) {
					session.setAttribute("login", userVO);
					return true;
				}//if
				
			}//if
			
			response.sendRedirect("/sample/user/login");  // 로그인 상태가 아니라면 로그인 페이지로 이동 시킨다.
			return false;
		}
		return true;
	}
	
	
	
	
}
