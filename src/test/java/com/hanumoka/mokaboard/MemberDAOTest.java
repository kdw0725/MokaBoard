package com.hanumoka.mokaboard;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hanumoka.mokaboard.dao.MemberDAO;
import com.hanumoka.mokaboard.vo.MemberVO;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//mybatis dao 테스트 
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDAOTest {
	
	@Inject
	private MemberDAO dao;
	
	@Test
	public void testTime() throws Exception{
		System.out.println("======= :" + dao.getTime());
	}
	
	@Test
	public void testInsertMember() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setUserid("user07");
		vo.setUserpw("user07");
		vo.setUsername("USER07");
		vo.setEmail("user07@aaa.com");
		
		dao.insertMember(vo);
	}
}
