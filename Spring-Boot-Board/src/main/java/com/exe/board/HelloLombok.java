package com.exe.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


//�����ε��� �����ڸ� ����(������ final ���� �ʼ�)
//final ������ setter ��� ����
@RequiredArgsConstructor
@Getter
@Setter
public class HelloLombok {

	private final String name;
	private final int age;
	
	
	public static void main(String[] args) {
		
//		HelloLombok hk = new HelloLombok();
		
		HelloLombok hk = new HelloLombok("����",27);
		

//		hk.setName("�����");
//		hk.setAge(29);

		System.out.println(hk.getName());
		System.out.println(hk.getAge());
		
	}
	
	
	
}
