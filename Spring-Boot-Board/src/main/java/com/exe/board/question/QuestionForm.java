package com.exe.board.question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {

	@NotEmpty(message = "제목은 필수항목")
	@Size(max = 200)
	private String subject;
	
	@NotEmpty(message = "내용은 필수항목")
	private String content;
	
}
