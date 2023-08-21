package com.web.oauth.base.service;

import javax.servlet.http.HttpSession;
import javax.xml.ws.handler.Handler;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.web.oauth.base.dto.SessionUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver{
	
	private final HttpSession httpSession;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		boolean isLoginUserAnnotation =
				parameter.getParameterAnnotation(LoginUser.class)!=null;
		
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
		
		
		return isLoginUserAnnotation && isUserClass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		
		
		
		
		return httpSession.getAttribute("user");
	}
	
	
	

}
