package com.exe.springdi2;

public class MessageCall {

	public static void main(String[] args) {
		
		//1.
		MessageEn ob1 = new MessageEn();
		ob1.sayHello("yihn");
		
		
		MessageKr ob2 = new MessageKr();
		ob1.sayHello("ÀÌÇö");
		
		
		//2.
		Message ms = null;
		
		ms = new MessageEn();
		ms.sayHello("hyeon");
		
		ms = new MessageKr();
		ms.sayHello("Çö");
		
		
		
		
		
	}
}
