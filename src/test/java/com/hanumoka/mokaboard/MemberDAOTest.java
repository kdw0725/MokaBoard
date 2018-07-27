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
		System.out.println("Log4j 동작 테스트");
		System.out.println("DB 시간:" + memberDAO.getTime());
	}
	
	@Test
	public void testInsertMember() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setUserid("user10");
		vo.setUserpw("user10");
		vo.setUsername("USER10");
		vo.setEmail("user10@aaa.com");
		
		memberDAO.insertMember(vo);
	}
}
