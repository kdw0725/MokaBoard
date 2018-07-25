package com.hanumoka.mokaboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/*@Inject
	private UserService service;*/
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerGet(Model model) throws Exception{
		logger.info("user register get.......");
		
		return "/user/register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerPost(Model model) throws Exception{
		logger.info("user register post.......");
		
		return "/user/register";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginGet(Model model) throws Exception{
		logger.info("user login get.......");
		
		return "/user/login";
	}
	
	
}