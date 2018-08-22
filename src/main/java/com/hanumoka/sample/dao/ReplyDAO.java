package com.hanumoka.sample.dao;

import java.util.List;

import com.hanumoka.sample.vo.Criteria;
import com.hanumoka.sample.vo.ReplyVO;

public interface ReplyDAO {

	public List<ReplyVO> list(Integer bno) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer rno) throws Exception;
	
	/* 댓글 페이징 */
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	
	/* 댓글 페이징을 위한 게시글 별 댓글 카운팅 */
	public int count(Integer bno) throws Exception;
	
}
