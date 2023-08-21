package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/board")
	@ResponseBody
	public String hello() {
		
		return "�ȳ� ȯ����";
		
	}
	
	@RequestMapping("/")
	public String home() {
		
		return "redirect:/question/list";
	}
}


/*
JPA(Java Persistence API)
JPA ORM�� ����ϸ� �����ڰ� ������ ���� �ۼ����� �ʾƵ�
������ ���̽��� �����͸� ó���� �� ����
JPA�� �������̽�, ���� �������̽��� �����ϴ� ���� Ŭ������ �ʿ�


SQL
insert into question (subject,content) values ("�ȳ�","�����������ƴ�?");
insert into question (subject,content) values ("�����̴�","�����̴�~");


JPA
Repository ��ü�� �����ؼ� ��

Question q1 = new Question();
q1.setSubject("�ȳ�");
q1.setContent("�����������ƴ�?);
this.questionRepository.save(q1);

Question q2 = new Question();
q2.setSubject("�����̴�");
q2.setContent("�����̴�~);
this.questionRepository.save(q2);


h2�����ͺ��̽��� �ַ� ���߿�, �ұԸ� ������Ʈ���� ����ϴ�
���� ��� �淮 �����ͺ��̽�(���� �ȿ� �����)


[Ư¡]
H2 DB�� �ڹٱ���� �淮ȭ�� �����ͺ��̽�
���Ϸ� �����ؼ� ���� DBó�� ������ ���� ����
�޸� DB�� ����ؼ� ���� �ν��Ͻ��� �����ϴ� �������� ����
������Ʈ �ʱ� ���߿��� �׽�Ʈ DB�� ���


*/




