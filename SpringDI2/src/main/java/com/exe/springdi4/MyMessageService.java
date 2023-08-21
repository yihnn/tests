package com.exe.springdi4;

import org.springframework.stereotype.Component;

//지정안하면 이름이 MyMessageService로 설정됨
@Component("messageService")
public class MyMessageService implements MessageService{

	
	public String getMessage() {
		
		return "안녕 오늘 수요일이래";
	}
}
