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
	@NotEmpty(message = "�����ID�� �ʼ���")
	private String userName;
	
	@NotEmpty(message = "����� �ʼ���")
	private String password1;

	@NotEmpty(message = "��� Ȯ�� �ʼ���")
	private String password2;

	//@Email : �̸��� ���� üũ
	@NotEmpty(message = "�̸��ϵ� �� �ʼ���")
	@Email
	private String email;

}
