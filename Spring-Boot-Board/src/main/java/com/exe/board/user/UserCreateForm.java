package com.exe.board.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자ID는 필수여")
	private String userName;
	
	@NotEmpty(message = "비번도 필수여")
	private String password1;

	@NotEmpty(message = "비번 확인 필수여")
	private String password2;

	//@Email : 이메일 형식 체크
	@NotEmpty(message = "이메일도 써 필수여")
	@Email
	private String email;

}
