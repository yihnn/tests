package com.exe.board.answer;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {

	@NotEmpty(message = "내용은 필수항목이라니께")
	private String content;
	
}
