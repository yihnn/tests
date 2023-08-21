package com.exe.springdi4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTimeService implements TimeService{
	
	public String getTimeString() {
		
		//캘린더 써도 됨
		SimpleDateFormat sdf = 
				(SimpleDateFormat)SimpleDateFormat.
				getDateTimeInstance(SimpleDateFormat.LONG,SimpleDateFormat.LONG);
		
		String now = sdf.format(new Date());
		
		//년월일 시간 반환
		return now;
	}

}
