package com.hanumoka.mokaboard.dao;

import com.hanumoka.mokaboard.vo.MemberVO;

public interface MemberDAO {

	public String getTime();
	public void insertMember(MemberVO vo);
	
	public MemberVO readMember(String userid) throws Exception;
	public MemberVO readWidthPW(String userid, String userpw) throws Exception;
	
}
