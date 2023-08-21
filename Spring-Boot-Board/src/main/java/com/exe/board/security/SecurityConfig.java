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

//2.환경설정
//3.모든 url 요청은 security의 제어를 받게 됨
//4. @PreAuthorize("isAuthenticated")를 활성화시키는 명령어
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	
	private final UserSecurityService userSecurityService;
	
	//메소드를 클래스화 시킴(객체 생성)->메모리상에 객체화 -> 주소생성
	//@configuration에서만 @Bean 사용 거눙
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//모든 인증되지 않은 요청을 허락
		http
		//아래 주소는 모두 허락하고
		.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		//h2-console이 들어간 아래 주소는 무시해
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
	
	//AuthenticationManager는 스프링 시큐리티의 인증 담당
	//UserSecurityService와 PasswordEncoder가 자동으로 설정이 됨
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
}
