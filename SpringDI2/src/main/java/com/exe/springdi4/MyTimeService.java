package com.exe.springdi4;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MyTimeService implements TimeService{
	
	public String getTimeString() {
		
		//Ķ���� �ᵵ ��
		SimpleDateFormat sdf = 
				(SimpleDateFormat)SimpleDateFormat.
				getDateTimeInstance(SimpleDateFormat.LONG,SimpleDateFormat.LONG);
		
		String now = sdf.format(new Date());
		
		//����� �ð� ��ȯ
		return now;
	}

}
