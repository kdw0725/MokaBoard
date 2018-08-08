package com.hanumoka.sample.controller;

import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register post......");
		logger.info(board.toString());
		
		service.regist(board);
		
		//model.addAttribute("result", "success");
		
		rttr.addFlashAttribute("msg", "success");
		
		//return "/samples/board/success";
		return "redirect:/samplehome/board/listAll";
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAll(Locale locale, Model model) throws Exception {
		
		logger.info("show all list........");
		
		model.addAttribute("list", service.listAll());
		
		//double e1 = 1 / 0;
		
		
		return "/samples/board/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("sampleboard read bno:" + bno);
		
		model.addAttribute("boardVO", service.read(bno));
		return "/samples/board/read";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("sampleboard read bno:" + bno);
		
		model.addAttribute("boardVO", service.read(bno));
		return "/samples/board/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("modify post......");
		logger.info(board.toString());
		
		service.modify(board);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/samplehome/board/listAll";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePOST(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		logger.info("remove post......");
		
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/samplehome/board/listAll";
	}
	
/*	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/samplehome/board/listAll";
	}*/
	
	

	
	
	
	
	
	
	
	
	
}
