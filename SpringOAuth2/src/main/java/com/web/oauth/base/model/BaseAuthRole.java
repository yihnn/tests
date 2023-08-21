package com.web.oauth.base.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseAuthRole {
	
	//ROLE_GUEST->key ,손님->title
	GUEST("ROLE_GUEST","손님"),
	USER("ROLE_USER","일반 사용자");
	
	private final String key;
	private final String title;
	
	
	
}