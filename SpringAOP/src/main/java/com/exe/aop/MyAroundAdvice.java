package com.exe.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAroundAdvice {

	@Around("execution(public void com..aop.*.*(..))")
	public Object aroundMethodCall(ProceedingJoinPoint joinPoint) {
		
		Object result = null;
		
		try {
			
			System.out.println("메소드 실행 전~!(Around)");
			result = joinPoint.proceed();	//메소드 실행(반환값 돌려받음)
			System.out.println("메소드 실행 후다!(Around)");
			
		} catch (Throwable e) {
			
		}
		
		return result;
	}
}
