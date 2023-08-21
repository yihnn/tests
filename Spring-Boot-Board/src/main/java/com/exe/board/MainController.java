package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/board")
	@ResponseBody
	public String hello() {
		
		return "안녕 환영혀";
		
	}
	
	@RequestMapping("/")
	public String home() {
		
		return "redirect:/question/list";
	}
}


/*
JPA(Java Persistence API)
JPA ORM을 사용하면 개발자가 쿼리를 직접 작성하지 않아도
데이터 베이스의 데이터를 처리할 수 있음
JPA는 인터페이스, 따라서 인터페이스를 구현하는 실제 클래스가 필요


SQL
insert into question (subject,content) values ("안녕","내가누군지아니?");
insert into question (subject,content) values ("이현이다","이현이다~");


JPA
Repository 객체에 저장해서 씀

Question q1 = new Question();
q1.setSubject("안녕");
q1.setContent("내가누군지아니?);
this.questionRepository.save(q1);

Question q2 = new Question();
q2.setSubject("이현이다");
q2.setContent("이현이다~);
this.questionRepository.save(q2);


h2데이터베이스는 주로 개발용, 소규모 프로젝트에서 사용하는
파일 기반 경량 데이터베이스(파일 안에 저장됨)


[특징]
H2 DB는 자바기반의 경량화된 데이터베이스
파일로 저장해서 실제 DB처럼 데이터 유지 가능
메모리 DB로 사용해서 실제 인스턴스가 동작하는 순간에만 유지
프로젝트 초기 개발에서 테스트 DB로 사용


*/




