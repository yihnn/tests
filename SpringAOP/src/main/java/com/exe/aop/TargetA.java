package com.exe.aop;

import org.springframework.stereotype.Component;

@Component("targetA")
public class TargetA {

	public void doSomething1(int a, String str) {
		System.out.println("TargetA.doSomething1");
	}
	
	public void doSomething2() {
		System.out.println("TargetA.doSomething2");
	}

	public void doAnother1() {
		System.out.println("TargetA.doAnother1");
	}

	public void doAnother2() {
		System.out.println("TargetA.doAnother2");
	}
	
	
	
	
}
