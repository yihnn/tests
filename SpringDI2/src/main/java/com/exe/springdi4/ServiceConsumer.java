package com.exe.springdi4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//객체 생성
@Component("serviceConsumer")
public class ServiceConsumer {

	//의존성 주입
	//지정된 이름 갖다 넣어
	@Autowired
	@Qualifier("messageService")
	MessageService ms;
	
	//알아서 이름 똑같은 애 갖다 넣어
	@Autowired
	TimeService ts;

	@Autowired
	JobService js;
	
	
	public void consumerService() {
		
//		GenericXmlApplicationContext context =
//				new GenericXmlApplicationContext("app-context.xml");
		
//		//app-context에서 들어오는 messageService
//		MessageService ms = (MessageService)context.getBean("messageService");
		
		String message = ms.getMessage();
		System.out.println(message);
		
		
		String time = ts.getTimeString();
		System.out.println(time);
		
		js.getJob();
		
	}
}
