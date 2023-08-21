package com.exe.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAfterReturningAdvice {

	@AfterReturning("execution(public void com..aop.*.*(..))")
	public void afterReturningMethodCall(){
		System.out.println("메소드 정상 실행 후~!!");
	}
	
	
}
