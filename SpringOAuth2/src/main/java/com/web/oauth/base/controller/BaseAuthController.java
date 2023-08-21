package com.web.oauth.base.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.oauth.base.dto.SessionUser;
import com.web.oauth.base.service.LoginUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BaseAuthController {

	private final HttpSession httpSession;
	
	//@LoginUser로 Session값을 받아옴
	//(아래 SessionUser user로 세션 생성을 대신함)
	@GetMapping("/")
	public String index(Model model,@LoginUser SessionUser user) {
		
		//세션이 필요할 때마다 써줘야함
		//@어노테이션으로 만들어서 사용할 수도 있음
		//SessionUser user = (SessionUser)httpSession.getAttribute("user");
		
		if(user!=null) {
			
			model.addAttribute("email", user.getEmail());
			model.addAttribute("name", user.getName());
			model.addAttribute("picture", user.getPicture());
		}
		
		return "index";
	}
}
