package com.hanumoka.sample.service;

import java.util.Date;

import com.hanumoka.sample.dto.LoginDTO;
import com.hanumoka.sample.vo.UserVO;

public interface UserService {

	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next) throws Exception;
	
	public UserVO checkLoginBefore(String value);
	
}
