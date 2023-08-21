package com.exe.board.user;

import lombok.Getter;

@Getter
public enum UserRole {

	//ROLE_로 시작해야 함(예약어)
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	
	private String value;
	
	UserRole(String value) {
		this.value = value;
	}
	
}
