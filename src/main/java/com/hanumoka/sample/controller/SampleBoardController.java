package com.hanumoka.sample.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hanumoka.sample.service.BoardService;
import com.hanumoka.sample.vo.BoardVO;
import com.hanumoka.sample.vo.Criteria;
import com.hanumoka.sample.vo.PageMaker;


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
		
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/samplehome/board/listPage";
	}
	
	
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public String listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		System.out.println("==============");
		System.out.println("cri:" + cri.toString());
		
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));  // 게시판의 글 리스트
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		//pageMaker.setTotalCount(131);  //테스트를 위해 임의로 지정;
		
		
		System.out.println("pageMaker:" + pageMaker.toString());
		
		model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		
		return "/samples/board/listPage";
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public String readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		logger.info("sampleboard read bno:" + bno);
		
		model.addAttribute("boardVO", service.read(bno));
		return "/samples/board/readPage";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public String modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri ,Model model) throws Exception {
		
		model.addAttribute("boardVO", service.read(bno));
		return "/samples/board/modifyPage";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {
		
		System.out.println("========== modifyPagePOST");
		
		logger.info("modify post......");
		logger.info(board.toString());
		
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/samplehome/board/listPage";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePOST(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		logger.info("remove post......");
		
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/samplehome/board/listPage";
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePagePOST(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("removePage post......");
		
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/samplehome/board/listPage";
	}
	
	
	
/*	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/samplehome/board/listAll";
	}*/
	
	/*@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAll(Locale locale, Model model) throws Exception {
		
		logger.info("show all list........");
		
		model.addAttribute("list", service.listAll());
		
		return "/samples/board/listAll";
	}*/
	
	/*@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public String listCri(Criteria cri, Model model) throws Exception {
		
		logger.info("show listCri........");
		
		model.addAttribute("list", service.listCriteria(cri));
		
		return "/samples/board/listCri";
	}*/

	
	/*@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("sampleboard read bno:" + bno);
		
		model.addAttribute("boardVO", service.read(bno));
		return "/samples/board/modify";
	}*/
	
	/*@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("modify post......");
		logger.info(board.toString());
		
		service.modify(board);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/samplehome/board/listPage";
	}*/
	
	/*@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("sampleboard read bno:" + bno);
		
		model.addAttribute("boardVO", service.read(bno));
		return "/samples/board/read";
	}*/
	
	
	
	
	
	
	
}
