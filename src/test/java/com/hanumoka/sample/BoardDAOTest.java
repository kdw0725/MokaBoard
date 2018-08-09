package com.hanumoka.sample;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.hanumoka.sample.dao.BoardDAO;
import com.hanumoka.sample.vo.BoardVO;
import com.hanumoka.sample.vo.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void testCreate() throws Exception{
		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다.");
		board.setContent("새로운 글을 넣습니다.");
		board.setWriter("user00");
		dao.create(board);
	}
	
	@Test
	public void testRead() throws Exception{
		logger.info(dao.read(2).toString());
	}
	
	@Test
	public void testUpdate() throws Exception{
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수저된 글입니다");
		board.setContent("수정 테스트");
		dao.update(board);
	}
	
	@Test
	public void testDelete() throws Exception{
		dao.delete(1);
	}
	
	//게시판 페이징 조회 테스튼
	@Test
	public void testListPage() throws Exception{
		
		int page = 3;
		
		List<BoardVO> list = dao.listPage(page);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	
	//Criteria를 페이징 적용한 게시판 테스트
	@Test
	public void testListCritera() throws Exception{
		
		Criteria cri = new Criteria();
		cri.setPage(1);
		cri.setPerPageNum(20);
		
		logger.info("cri 값: " +cri.toString());
		
		List<BoardVO> list = dao.listCriteria(cri);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
		}
		
	}//
	
	@Test
	public void testURI() throws Exception{
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/samplehome/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();
		
		logger.info("/samplehome/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}
	
	@Test
	public void testURI2() throws Exception{
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/{a}/{b}/{c}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("samplehome", "board", "read")
				.encode();
		
		logger.info("/samplehome/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}
}
