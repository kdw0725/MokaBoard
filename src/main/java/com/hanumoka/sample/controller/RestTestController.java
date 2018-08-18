package com.hanumoka.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resttest")
public class RestTestController {

	@RequestMapping(value = "test", produces = "text/html; charset=utf8")
	public String restText() {
		return "rest 단순 문자열 반환";
	}
	
	@RequestMapping("/json")
	public SampleVO restJson() {
		
		SampleVO sampleVO = new SampleVO();
		sampleVO.setNo(1);
		sampleVO.setName("제이슨 객체입니다.");
		return sampleVO;
		
	}
	
	@RequestMapping("/jsonList")
	public List<SampleVO> sendList(){
		
		List<SampleVO> list = new ArrayList<>();
		
		for(int i =0; i < 10; i++) {
			SampleVO sampleVO = new SampleVO();
			sampleVO.setName( (i+1) +"번 데이터");
			sampleVO.setNo(i+1);
			list.add(sampleVO);
		}
		
		return list;
	}
	
	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap(){
		
		Map<Integer, SampleVO> map = new HashMap<>();
		
		for(int i =0; i < 10; i++) {
			SampleVO sampleVO = new SampleVO();
			sampleVO.setName( (i+1) +"번 데이터");
			sampleVO.setNo(i+1);
			map.put(i, sampleVO);
		}
		
		return map;
	}
	
	
	
}

class SampleVO{
	
	private Integer no;
	private String name;
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
