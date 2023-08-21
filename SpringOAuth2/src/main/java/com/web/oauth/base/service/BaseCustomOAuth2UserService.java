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
		
		//���� �α����� �����ϴ� �÷��� �ڵ�(google,kakao,naver...)
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		//OAuth2 �α��� ����� key�� �Ǵ� �ʵ尪(PK ����)
		//����: sub, ���̹�: response, īī��: id �߿� �ϳ��� userNameAttributeName�� ����
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();
		
		System.out.println(userNameAttributeName);	//sub,response,id
		
		//OAuthAttributes ������ ����
		//�α����� ���� ������ Oauth2User�� �Ӽ��� ��Ƶδ� of�޼ҵ�
		OAuthAttributes attributes = 
				OAuthAttributes.of(registrationId, userNameAttributeName, oauth2User.getAttributes());
		
		
		System.out.println(attributes.getAttributes());	//json������ ������
		
		//������� �Ӽ��� AuthUser��ü�� ����
		BaseAuthUser authUser = saveOrUpdate(attributes);
		 	
		//SessionUser : ���ǿ� ����� ������ �����ϱ� ���� DTO Ŭ����
		httpSession.setAttribute("user", new SessionUser(authUser));
		
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(authUser.getRoleKey())),
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}
		
	
	//���� ����� ������ ������Ʈ �Ǿ��� �� ����� �޼ҵ�
	//������� �̸��̳� ������ ������ ����Ǹ� User ��ƼƼ���� �ݿ���
	private BaseAuthUser saveOrUpdate(OAuthAttributes attributes) {
		
		BaseAuthUser authUser =
				baseAuthUserRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());
		
		//DB�� save
		return baseAuthUserRepository.save(authUser);
	}

}
