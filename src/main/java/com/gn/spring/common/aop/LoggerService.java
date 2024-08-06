package com.gn.spring.common.aop;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class LoggerService {
	
	Logger LOGGER = LogManager.getLogger(LoggerService.class);
	
	// 특정 메소드 실행 전에 동작할 코드
	public void loggerBefore(JoinPoint jp) {
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		LOGGER.info(className + " . " + methodName + " () 실행 전");
	}
	
	// 특정 메소드 실행 후에 동작할 메소드
	public void loggerAfter(JoinPoint jp) {
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		LOGGER.info(className + " . " + methodName + " () 실행 후");
	}
}
