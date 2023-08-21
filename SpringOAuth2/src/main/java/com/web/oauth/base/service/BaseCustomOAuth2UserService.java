package com.web.oauth.base.service;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.mapping.MetaAttributable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.web.oauth.base.dao.BaseAuthUserRepository;
import com.web.oauth.base.dto.OAuthAttributes;
import com.web.oauth.base.dto.SessionUser;
import com.web.oauth.base.model.BaseAuthUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseCustomOAuth2UserService 
implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	
	@Autowired
	private final BaseAuthUserRepository baseAuthUserRepository;

	@Autowired
	private final HttpSession httpSession;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) 
			throws OAuth2AuthenticationException {
		
		OAuth2UserService<OAuth2UserRequest, OAuth2User> oauthUserService =
				new DefaultOAuth2UserService();
		OAuth2User oauth2User = oauthUserService.loadUser(userRequest);
		
		//간편 로그인을 진행하는 플랫폼 코드(google,kakao,naver...)
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		//OAuth2 로그인 진행시 key가 되는 필드값(PK 역할)
		//구글: sub, 네이버: response, 카카오: id 중에 하나가 userNameAttributeName에 들어옴
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();
		
		System.out.println(userNameAttributeName);	//sub,response,id
		
		//OAuthAttributes 파일을 생성
		//로그인을 통해 가져온 Oauth2User의 속성을 담아두는 of메소드
		OAuthAttributes attributes = 
				OAuthAttributes.of(registrationId, userNameAttributeName, oauth2User.getAttributes());
		
		
		System.out.println(attributes.getAttributes());	//json형태의 데이터
		
		//응답받은 속성을 AuthUser객체에 넣음
		BaseAuthUser authUser = saveOrUpdate(attributes);
		 	
		//SessionUser : 세션에 사용자 정보를 저장하기 위한 DTO 클래스
		httpSession.setAttribute("user", new SessionUser(authUser));
		
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(authUser.getRoleKey())),
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}
		
	
	//구글 사용자 정보가 업데이트 되었을 때 대비한 메소드
	//사용자의 이름이나 프로필 사진이 변경되면 User 엔티티에도 반영됨
	private BaseAuthUser saveOrUpdate(OAuthAttributes attributes) {
		
		BaseAuthUser authUser =
				baseAuthUserRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());
		
		//DB에 save
		return baseAuthUserRepository.save(authUser);
	}

}
