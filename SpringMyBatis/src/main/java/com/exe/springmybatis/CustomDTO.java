package com.exe.springmybatis;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@Data: Getter&Setter 한 번에 만들어주는 어노테이션
//@ToString: String.format 자동생성(출력문)


@Data
@ToString
public class CustomDTO {
	//아래처럼 어노테이션 써도 게터세터 생성됨
	//public @Data class CustomDTO {

	
	//private @Getter int id; (해당 변수의 게터만 만듦)
	private  int id;
	private String name;
	private int age;
	
	
	//getter/setter 안 만들어도 lombok가 자동 생성
	//셋팅 필요(2가지 방법이 있음)
	
	
	
	
	
	
}
