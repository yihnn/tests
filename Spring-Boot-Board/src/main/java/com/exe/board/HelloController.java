package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//return���� ����/�����̸� ���� �ν�
//"Hello World"��� �ϸ� jsp������ �ν�
//@ResponseBody : text�� �ν� ��Ŵ
//@Controller

@RestController		//@Controller+@ResponseBody
public class HelloController {

	@RequestMapping("/hello")
	//@ResponseBody
	
	public String hello() {
		
		return "���̴� �ڹپ�";
		
	}
	
}
