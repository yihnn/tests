package com.web.oauth.base.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseAuthRole {
	
	//ROLE_GUEST->key ,�մ�->title
	GUEST("ROLE_GUEST","�մ�"),
	USER("ROLE_USER","�Ϲ� �����");
	
	private final String key;
	private final String title;
	
	
	
}