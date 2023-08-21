package com.exe.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.exe.board.user.UserSecurityService;

import lombok.RequiredArgsConstructor;

//2.ȯ�漳��
//3.��� url ��û�� security�� ��� �ް� ��
//4. @PreAuthorize("isAuthenticated")�� Ȱ��ȭ��Ű�� ��ɾ�
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	
	private final UserSecurityService userSecurityService;
	
	//�޼ҵ带 Ŭ����ȭ ��Ŵ(��ü ����)->�޸𸮻� ��üȭ -> �ּһ���
	//@configuration������ @Bean ��� �Ŵ�
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//��� �������� ���� ��û�� ���
		http
		//�Ʒ� �ּҴ� ��� ����ϰ�
		.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		//h2-console�� �� �Ʒ� �ּҴ� ������
		.csrf().ignoringAntMatchers("/h2-console/**")
		.and()
		.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		.and()
		.formLogin().loginPage("/user/login").defaultSuccessUrl("/")
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/").invalidateHttpSession(true)
		;
		
		
		return http.build();
		
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//AuthenticationManager�� ������ ��ť��Ƽ�� ���� ���
	//UserSecurityService�� PasswordEncoder�� �ڵ����� ������ ��
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
}
