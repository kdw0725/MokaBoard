package com.hanumoka.sample.controller;

import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanumoka.sample.service.BoardService;
import com.hanumoka.sample.vo.BoardVO;


@Controller
@RequestMapping("/samplehome/board/*")
public class SampleBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleBoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET(BoardVO board, Model model) {
		logger.info("register get......");
		return "/samples/board/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, Model model) throws Exception {
		logger.info("register post......");
		logger.info(board.toString());
		
		service.regist(board);
		
		model.addAttribute("result", "success");
		
		//return "/samples/board/success";
		return "redirect:/samplehome/board/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		return "/samples/board/list";
	}
	
	@RequestMapping(value = "/mod", method = RequestMethod.GET)
	public String mod(Locale locale, Model model) {
		return "/samples/board/mod";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Locale locale, Model model) {
		return "/samples/board/read";
	}
	
	
	
	
}
