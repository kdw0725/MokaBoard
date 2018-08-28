package com.hanumoka.sample.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hanumoka.sample.dao.UserDAO;
import com.hanumoka.sample.dto.LoginDTO;
import com.hanumoka.sample.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		dao.keepLogin(uid, sessionId, next);
	}

	@Override
	public UserVO checkLoginBefore(String value) {
		return dao.checkUserWithSessionKey(value);
	}

}
