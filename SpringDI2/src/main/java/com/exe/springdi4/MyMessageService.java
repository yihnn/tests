package com.exe.springdi4;

import org.springframework.stereotype.Component;

//�������ϸ� �̸��� MyMessageService�� ������
@Component("messageService")
public class MyMessageService implements MessageService{

	
	public String getMessage() {
		
		return "�ȳ� ���� �������̷�";
	}
}
