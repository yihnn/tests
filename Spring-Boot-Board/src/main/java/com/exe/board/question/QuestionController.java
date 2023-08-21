package com.exe.board.question;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.exe.board.answer.AnswerForm;
import com.exe.board.user.SiteUser;
import com.exe.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor		//생성자 오버로딩(의존성 주입을 위함)
@Controller
public class QuestionController {

	//private final QuestionRepository questionRepository;
	
	private final QuestionService questionService;
	private final UserService userService;
	
	
	@RequestMapping("/list")
	public String list(Model model,@PageableDefault Pageable pageable) {
		
		//List<Question> lists = questionRepository.findAll();
		Page<Question> paging = questionService.getLists(pageable);
		
		model.addAttribute("paging",paging);
		
		return "question_list";
	}
	
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model,@PathVariable("id") Integer id,
			AnswerForm answerForm) {
	
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question",question);
				
		return "question_detail";
	}
	
	//인증된 사람만 글 쓸 수 있음
	@PreAuthorize("isAuthenticated")
	@GetMapping("/create")
	public String questionCreat(QuestionForm questionForm) {
		
		
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm,
			BindingResult bindingResult,Principal principal) {
		
		if(bindingResult.hasErrors()) {
			
			return "question_form";
		}
		
		SiteUser siteUser = userService.getUser(principal.getName());
		
		questionService.create(questionForm.getSubject(), 
				questionForm.getContent(),siteUser);
		
		return "redirect:/question/list";
				
	}
	
	
	@PreAuthorize("isAuthenticated")
	   @GetMapping("/modify/{id}")
	   public String questionModify(QuestionForm questionForm, 
	         @PathVariable("id") Integer id, Principal principal) {
	      
	      Question question = questionService.getQuestion(id);
	      
	      if(!question.getAuthor().getUserName().equals(principal.getName())) {
	         throw new ResponseStatusException(
	               HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");   
	      }
	      
	      questionForm.setSubject(question.getSubject());
	      questionForm.setContent(question.getContent());
	      
	      return "question_form";
	      
	   }
	   
	   @PreAuthorize("isAuthenticated")
	   @PostMapping("/modify/{id}")
	   public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,  
	         @PathVariable("id") Integer id, Principal principal) {
	      
	      if(bindingResult.hasErrors()) {
	         return "question_form";
	      }
	      
	      Question question = questionService.getQuestion(id);
	      
	      if(!question.getAuthor().getUserName().equals(principal.getName())) {
	         throw new ResponseStatusException(
	               HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");   
	      }
	      
	      questionService.modify(question, 
	            questionForm.getSubject(), questionForm.getContent());
	      
	      return String.format("redirect:/question/detail/%s", id);
	      
	   }
	   
	   
	   @PreAuthorize("isAuthenticated")
	   @GetMapping("/delete/{id}")
	   //Principal : 누가 로그인한 건지 상태를 알려줌
	   public String questionDelete(Principal principal, 
			   @PathVariable Integer id) {
		   
		   Question question = questionService.getQuestion(id);
		   
		   if(!question.getAuthor().getUserName().equals(principal.getName())) {
			   	throw new ResponseStatusException
			   	(HttpStatus.BAD_REQUEST,"삭제할 권한 없슈");
			   	
		   }
		   
		  questionService.delete(question);

		  return "redirect:/";
	   }
	   
	   
	   @PreAuthorize("isAuthenticated")
	   @GetMapping("/vote/{id}")
	   //Principal : 누가 로그인한 건지 상태를 알려줌
	   public String questionVote(Principal principal, 
			   @PathVariable Integer id) {
		   
		  Question question = questionService.getQuestion(id);
		   
		  SiteUser siteUser = userService.getUser(principal.getName());
		   
		  questionService.vote(question,siteUser);

		  return String.format("redirect:/question/detail/%s", id);
	   }
	   
}