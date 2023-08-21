package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//return값을 폴더/파일이름 으로 인식
//"Hello World"라고 하면 jsp등으로 인식
//@ResponseBody : text로 인식 시킴
//@Controller

@RestController		//@Controller+@ResponseBody
public class HelloController {

	@RequestMapping("/hello")
	//@ResponseBody
	
	public String hello() {
		
		return "흥이다 자바야";
		
	}
	
}
