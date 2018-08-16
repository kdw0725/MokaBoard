package com.hanumoka.sample.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanumoka.sample.service.BoardService;
import com.hanumoka.sample.vo.PageMaker;
import com.hanumoka.sample.vo.SearchCriteria;

@Controller
@RequestMapping("/sample/sboard/*")
public class SearchBoardController {
	
private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	//TODO: 게시판 검색영역 앞에 select box 생성하기
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPageGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));  // 게시판의 글 리스트
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		
		return "/samples/sboard/list";
	}

}
