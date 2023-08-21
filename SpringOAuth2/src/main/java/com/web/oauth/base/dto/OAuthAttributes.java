package com.web.oauth.base.dto;

import java.util.Map;

import com.web.oauth.base.model.BaseAuthRole;
import com.web.oauth.base.model.BaseAuthUser;

import lombok.Builder;
import lombok.Getter;


@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
			String name, String email, String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
	
	
	
			//ofGoogle 메소드에서 변환 작업을 함
			//of는 문법(변경 불가)
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {

		
		if(registrationId.equals("kakao")) {	//id
			
			return ofKakao(userNameAttributeName,attributes);
		}
		
		if(registrationId.equals("naver")) {	//response
			
			return ofNaver("id",attributes);
		}
		
		
	//이곳에서 구글,카카오,네이버 등을 구분하는 작업(ofGoogle,ofNaver,ofKakao)
	//userNameAttributeName : sub =
		return ofGoogle(userNameAttributeName,attributes);
		
	}
	
	
	//OAuth2User에서 반환하는 사용자 정보는 MAP형태이기 때문에
	private static OAuthAttributes ofGoogle(String userNameAttributeName, 
			Map<String, Object> attributes) {
		
		return OAuthAttributes.builder()
				.name((String)attributes.get("name"))
				.email((String)attributes.get("email"))
				.picture((String)attributes.get("picture"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
		
	}
	
	
	private static OAuthAttributes ofKakao(String userNameAttributeName,
			Map<String, Object> attributes) {
		
		//kakao_account에 사용장 정보(email)가 있음
		Map<String, Object> kakaoAccount = 
				(Map<String, Object>)attributes.get("kakao_account");
		
		//kakao_profile안에 profile이라는 json객체가 있어 꺼내줘야 함
		Map<String, Object> kakaoProfile = 
				(Map<String, Object>)kakaoAccount.get("profile");
		
		
		return OAuthAttributes.builder()
				.name((String)kakaoProfile.get("nickname"))
				.email((String)kakaoAccount.get("email"))
				.picture((String)kakaoProfile.get("profile_image_url"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
		
	}
	
	
	private static OAuthAttributes ofNaver(String userNameAttributeName,
			Map<String, Object> attributes) {
		
		//kakao_account에 사용장 정보(email)가 있음
		Map<String, Object> response = 
				(Map<String, Object>)attributes.get("response");
		
				
		return OAuthAttributes.builder()
				.name((String)response.get("name"))
				.email((String)response.get("email"))
				.picture((String)response.get("profile_image"))
				.attributes(response)
				.nameAttributeKey(userNameAttributeName)
				.build();
		
	}
	
	
	public BaseAuthUser toEntity() {
		
		return BaseAuthUser.builder()
				.name(name)
				.email(email)
				.picture(picture)
				.role(BaseAuthRole.GUEST)
				.build();
	}

}
