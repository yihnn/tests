package com.exe.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


//오버로딩된 생성자를 만듦(변수는 final 설정 필수)
//final 변수는 setter 사용 못함
@RequiredArgsConstructor
@Getter
@Setter
public class HelloLombok {

	private final String name;
	private final int age;
	
	
	public static void main(String[] args) {
		
//		HelloLombok hk = new HelloLombok();
		
		HelloLombok hk = new HelloLombok("이현",27);
		

//		hk.setName("기미현");
//		hk.setAge(29);

		System.out.println(hk.getName());
		System.out.println(hk.getAge());
		
	}
	
	
	
}
