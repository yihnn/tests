package com.exe.springdi4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ServiceConsumer {

	MessageService ms;
	TimeService ts;
	JobService js;
	
	//������ ����
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
		
//		//app-context���� ������ messageService
//		MessageService ms = (MessageService)context.getBean("messageService");
		
//		String message = ms.getMessage();
//		System.out.println(message);
		
		
		String time = ts.getTimeString();
		System.out.println(time);
		
		js.getJob();
		
	}
}
