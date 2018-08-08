package com.hanumoka.sample.dao;

import java.util.List;

import com.hanumoka.sample.vo.BoardVO;
import com.hanumoka.sample.vo.Criteria;

public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	//게시판 페이징 
	public List<BoardVO> listPage(int page) throws Exception; 
	
	//게시판 페이징용 Criteria
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	//토탈카운트를 반환?
	public int countPaging(Criteria cri) throws Exception;
	
}
