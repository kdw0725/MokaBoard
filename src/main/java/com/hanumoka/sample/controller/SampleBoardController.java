package com.hanumoka.sample.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SampleBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleBoardController.class);
	
	@RequestMapping(value = "/samplehome/board/list", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "/samples/board/list";
	}
	
	@RequestMapping(value = "/samplehome/board/mod", method = RequestMethod.GET)
	public String mod(Locale locale, Model model) {
		return "/samples/board/mod";
	}
	
	@RequestMapping(value = "/samplehome/board/read", method = RequestMethod.GET)
	public String read(Locale locale, Model model) {
		return "/samples/board/read";
	}
	
	@RequestMapping(value = "/samplehome/board/register", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		return "/samples/board/register";
	}
	
	
}
