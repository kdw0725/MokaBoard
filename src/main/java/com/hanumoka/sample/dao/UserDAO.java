package com.hanumoka.sample.dao;

import java.util.Date;

import com.hanumoka.sample.dto.LoginDTO;
import com.hanumoka.sample.vo.UserVO;

public interface UserDAO {

	//로그인 처리
	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next);
	
	public UserVO checkUserWithSessionKey(String value);
	
}
