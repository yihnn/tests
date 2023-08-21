package com.exe.springmybatis;

import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomMain {

	public static void main(String[] args) {

		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("app-context.xml");

		CustomDAO dao = (CustomDAO)context.getBean("customDAO");

		CustomDTO dto;

		
		/*
		//insert
		dto = new CustomDTO();
		dto.setId(888);
		dto.setName("Çô´Ï");
		dto.setAge(24);

		dao.insertData(dto); 

		System.out.println("ÀÎ¼­Æ® ¿Ï·ä");
		*/


		/*
		//select
		List<CustomDTO> lists = dao.getList();

		for(CustomDTO dto1 : lists) {
			System.out.printf("%d %s %d\n",
					dto1.getId(), dto1.getName(), dto1.getAge());

		}
			System.out.println("¼¿·º ¿Ï·ä");
		*/
		



		/*
		//µ¥ÀÌÅÍ ÇÏ³ª select
		dto = dao.getReadData(888);

		if(dto!=null) {
			System.out.println(dto);
			System.out.printf("%d %s %d\n",
					dto.getId(), dto.getName(), dto.getAge());
		}
		
		System.out.println("ÇÏ³ª ¼¿·º ¿Ï·ä");
		*/
		
		

		/*
		//µ¥ÀÌÅÍ ¼öÁ¤
		dto = new CustomDTO();
		dto.setId(888);;
		dto.setName("Çö");
		dto.setAge(29);


		dao.updateData(dto);


		System.out.println("¾÷µ« ¿Ï·ä");
		*/


		/*
		//»èÁ¦
		dao.deleteDate(433);
		*/
		
		
		List<CustomDTO> lists = dao.getList();

		for(CustomDTO dto1 : lists) {
			System.out.printf("%d %s %d\n",
					dto1.getId(), dto1.getName(), dto1.getAge());

		}

		System.out.println("¼¿·º ¿Ï·ä");

	}
	}

	


