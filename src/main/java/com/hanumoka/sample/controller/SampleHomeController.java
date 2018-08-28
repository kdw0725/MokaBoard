package com.hanumoka.sample.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SampleHomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleHomeController.class);
	
	/* Mokaboard 작업 home 화면 */
	@RequestMapping(value = {"/sample", "/sample/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("sample home log info");
		logger.trace("sample home log trace");
		logger.debug("sample home log debug");
		logger.error("sample home log error");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "/sample/home";
	}
	
	@RequestMapping(value = "/sample/doA", method = RequestMethod.GET)
	public String doA(Locale locale, Model model) {
		System.out.println("doA ---------------");
		return "/sample/home";
	}
	
	@RequestMapping(value = "/sample/doB", method = RequestMethod.GET)
	public String doB(Locale locale, Model model) {
		System.out.println("doB ---------------");
		model.addAttribute("result", "DOB RESULT");
		return "/sample/home";
	}
	
	
}
