package com.exe.springmybatis;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@Data: Getter&Setter �� ���� ������ִ� ������̼�
//@ToString: String.format �ڵ�����(��¹�)


@Data
@ToString
public class CustomDTO {
	//�Ʒ�ó�� ������̼� �ᵵ ���ͼ��� ������
	//public @Data class CustomDTO {

	
	//private @Getter int id; (�ش� ������ ���͸� ����)
	private  int id;
	private String name;
	private int age;
	
	
	//getter/setter �� ���� lombok�� �ڵ� ����
	//���� �ʿ�(2���� ����� ����)
	
	
	
	
	
	
}
