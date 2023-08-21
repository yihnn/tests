package com.exe.springdi3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageService {

	
	public void messageService() {
		
		//BeanFactory 생성
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("app-context.xml");
		
		//Bean 객체 획득
		//spring은 인터페이스 사용
		//다운 캐스팅 필요
		Message ms = (Message)context.getBean("message");
		ms.sayHello("기미현");
	}
}
