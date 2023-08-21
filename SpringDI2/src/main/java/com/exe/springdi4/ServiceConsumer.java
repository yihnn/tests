package com.exe.springdi4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//��ü ����
@Component("serviceConsumer")
public class ServiceConsumer {

	//������ ����
	//������ �̸� ���� �־�
	@Autowired
	@Qualifier("messageService")
	MessageService ms;
	
	//�˾Ƽ� �̸� �Ȱ��� �� ���� �־�
	@Autowired
	TimeService ts;

	@Autowired
	JobService js;
	
	
	public void consumerService() {
		
//		GenericXmlApplicationContext context =
//				new GenericXmlApplicationContext("app-context.xml");
		
//		//app-context���� ������ messageService
//		MessageService ms = (MessageService)context.getBean("messageService");
		
		String message = ms.getMessage();
		System.out.println(message);
		
		
		String time = ts.getTimeString();
		System.out.println(time);
		
		js.getJob();
		
	}
}
