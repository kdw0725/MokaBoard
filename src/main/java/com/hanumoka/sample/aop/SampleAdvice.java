package com.hanumoka.sample.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class SampleAdvice {

	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	//@Before("execution(* com.hanumoka.sample.service.BoardService*.*(..))")
	@Before("execution(* com.hanumoka.sample.service.*.*(..))")
	public void startLog(JoinPoint jp) {
		
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		/* 전달되는 모든 파라미터들을 Object의 배열로 가져온다. */
	    logger.info("1:" + Arrays.toString(jp.getArgs()));
	    
		/* 해당 Advice의 타입을 알아낸다. */
	    logger.info("2:" + jp.getKind());
	    
		/* 실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용 */
	    logger.info("3:" + jp.getSignature().getName());
	    
		/* target 객체를 알아낼 때 사용 */
	    logger.info("4:" + jp.getTarget().toString());
	    
		/* Advice를 행하는 객체를 알아낼 때 사용 */
	    logger.info("5:" + jp.getThis().toString());
	    
	}
	
	@Around("execution(* com.hanumoka.sample.service.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		//실제 타겟을 실행하는 부분이다. 이 부분이 없으면 advice가 적용된 메소드가 동작을 안할거 같다.
		Object result = pjp.proceed();  //proceed는 Exception 보다 상위 Throwable을 처리해야 한다.
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + ( endTime - startTime));
		logger.info("==============================");
		
		//Around를 사용할 경우 반드시 Object를 리턴해야 한다.
		return result;
	}
}
