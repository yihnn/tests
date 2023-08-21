package com.exe.board.user;

import lombok.Getter;

@Getter
public enum UserRole {

	//ROLE_�� �����ؾ� ��(�����)
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	
	private String value;
	
	UserRole(String value) {
		this.value = value;
	}
	
}
