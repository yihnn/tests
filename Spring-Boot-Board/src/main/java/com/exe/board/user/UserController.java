package com.exe.board.user;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		
		return "signup_form";
		
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, 
			BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			return "signup_form";
			
		}
		
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"2���� �н����尡 �ȸ¾�");
			
			return "signup_form";
		}
		
		try {
		
		userService.create(userCreateForm.getUserName(), userCreateForm.getEmail(),
				userCreateForm.getPassword1());
		} catch(DataIntegrityViolationException e) {
		
			e.printStackTrace();
			
			bindingResult.reject("signupFailed","�̹� ��ϵ� ����ڽ�");
			
			return "signup_form";
			
		} catch(Exception e) {
		
			e.printStackTrace();
			
			bindingResult.reject("signupFailed", e.getMessage());
			
			return "signup_form";
			
			
		}
			
			return "redirect:/";
	}
	
	//â�� ����ָ� spring�� ó��
	@GetMapping("/login")
	public String login() {
		
		
		
		return "login_form";
	}
	
	
	
}
