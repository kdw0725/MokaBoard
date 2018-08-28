package com.hanumoka.sample.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.hanumoka.sample.dto.LoginDTO;
import com.hanumoka.sample.service.UserService;
import com.hanumoka.sample.vo.UserVO;

@Controller
@RequestMapping("/sample/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
		logger.info("/sample/user/login : get...");
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(@ModelAttribute("dto") LoginDTO dto, HttpSession session, Model model) throws Exception {
		logger.info("/sample/user/login : POST...");
		
		UserVO vo = service.login(dto);
		
		if(vo == null) {
			return ;
		}//if
		
		//직접적인 로그인 처리는 Interceptor에서 한다.
		model.addAttribute("userVO", vo);
		
		if(dto.isUseCookie()) {
			int amount = 60 * 60 * 24 * 7;
			Date sessionLimit = new Date(System.currentTimeMillis()+(1000*amount));
			service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
		}// if
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Object obj = session.getAttribute("login");
		
		if(obj != null) {
			UserVO vo = (UserVO) obj;
			
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie!=null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUid(), session.getId(), new Date());
			}//if
			
		}//if
		
		return "/sample/user/logout";
		
	}
	
	
}
