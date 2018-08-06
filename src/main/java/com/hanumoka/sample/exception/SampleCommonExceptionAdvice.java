package com.hanumoka.sample.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/* 
 * 스프링 MVC에서 제공하는 @ControllerAdvice는 호출되는 메소드에서 발생된 Exception을 모두 처리하는 역할을 한다.
 * 만드는 방식은 다음과 같다.
 * 1.클래스에 @ControllerAdvice 라는 애노테이션 처리
 * 2.각 메소드에 @ExceptionHandler를 이용해서 적절한 타입의 Exception을 처리
 * 
 * @ControllerAdvice 애노테이션을 통해서 이 클래스의 객체가 컨트롤러에서 발생하는 Exception을 전문적으로 처리하는 클래스라는 것을 명시한다.
 * */

@ControllerAdvice  
public class SampleCommonExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(SampleCommonExceptionAdvice.class);
	
	/* common메소드는  Exception 타입으로 처리하는 모든 예외를 처리하도록 설정 */
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {
		
		logger.info(e.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/samples/errors/error_common");
		mav.addObject("exception", e);  //예외를 뷰에 던져서 주자.
		
		return mav;
	}
	
	
}
