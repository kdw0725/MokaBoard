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
@RequestMapping("/sample/sboard/*")
public class SearchBoardController {
	
private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	//게시글 리스트 조회
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPageGET(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));  // 게시판의 글 리스트
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		
		return "/sample/sboard/list";
	}
	
	
	//게시글 등록화면 이동
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET(BoardVO board, Model model, @ModelAttribute("cri") Criteria cri) {
		logger.info("register get......");
		return "/sample/sboard/register";
	}
	
	//게시글 등록
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register post......");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/sample/sboard/list";
	}
	
	//게시글 읽기 화면 이동
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public String readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		logger.info("sampleboard read bno:" + bno);
		
		model.addAttribute("boardVO", service.read(bno));
		return "/sample/sboard/readPage";
	}
	
	//게시글 수정화면 이동
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public String modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri ,Model model) throws Exception {
		
		model.addAttribute("boardVO", service.read(bno));
		return "/sample/sboard/modifyPage";
	}
	
	//게시글 수정
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {
		
		logger.info("modify post......");
		logger.info(board.toString());
		
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/sample/sboard/list";
	}
	
	//게시글 삭제
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePagePOST(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("removePage post......");
		
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/sample/sboard/list";
	}

}
