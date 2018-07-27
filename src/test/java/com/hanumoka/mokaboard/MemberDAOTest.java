package com.hanumoka.mokaboard;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.hanumoka.mokaboard.dao.MemberDAO;
import com.hanumoka.mokaboard.vo.MemberVO;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//mybatis dao 테스트 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDAOTest {
	
	@Inject
	private MemberDAO memberDAO;
	
	@Test
	public void testTime() throws Exception{
		System.out.println("======= :" + memberDAO.getTime());
	}
	
	@Test
	public void testInsertMember() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setUserid("user09");
		vo.setUserpw("user09");
		vo.setUsername("USER09");
		vo.setEmail("user09@aaa.com");
		
		memberDAO.insertMember(vo);
	}
}
