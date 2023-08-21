package com.exe.springdi4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ServiceConsumer {

	MessageService ms;
	TimeService ts;
	JobService js;
	
	//생성자 생성
	public ServiceConsumer() {}
		
	
	public ServiceConsumer(MessageService ms) {
		this.ms = ms;
	}
	
	public void setTimeService(TimeService ts) {
		this.ts = ts;
	}
	
	public void setJobService(JobService js) {
		this.js = js;
	}
	
	public void consumerService() {
		
//		GenericXmlApplicationContext context =
//				new GenericXmlApplicationContext("app-context.xml");
		
//		//app-context에서 들어오는 messageService
//		MessageService ms = (MessageService)context.getBean("messageService");
		
//		String message = ms.getMessage();
//		System.out.println(message);
		
		
		String time = ts.getTimeString();
		System.out.println(time);
		
		js.getJob();
		
	}
}
