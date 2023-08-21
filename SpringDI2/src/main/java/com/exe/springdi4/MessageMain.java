package com.exe.springdi4;

import org.springframework.context.support.GenericXmlApplicationContext;


/*
 * @Component : 자동으로 빈 등록

@Scope
자동으로 등록되는 빈의 범위 지정
singleton, prototype, session …

@Required
Setter 메서드에 지정해서 반드시 설정해야 하는 필수 프로퍼티 정의

@Autowired
Setter 메서드, 생성자 메서드 또는 필드(프로퍼티)에 직접 설정 해서 
자동으로 의존성 주입이 수행되도록 구성

@Autowired with (required = false) Option
컨테이너가 자동으로 의존성 주입을 수행할 때 대상 Bean을 발견하지 못해도
오류를 발생시키지 않도록 설정

@Qualifier
@Autowired와 함께 사용되어서 자동 의존성 주입이 수행될 대상 Bean을 구체적으로 설정
같은 타입의 Bean이 두 개 이상 등록된 경우 @Autowired에 발생할 수 있는 모호성 제거

@Resource
@Autowired + @Qualifier와 같은 효과
 * 
 */


public class MessageMain {

	public static void main(String[] args) {
	
//	ServiceConsumer sc = new ServiceConsumer();
//	sc.consumerService();
	
	GenericXmlApplicationContext context =
			new GenericXmlApplicationContext("app-context.xml");
	
	ServiceConsumer sc = (ServiceConsumer)context.getBean("serviceConsumer");
	sc.consumerService();
}
}