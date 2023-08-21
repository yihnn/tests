package com.exe.board.question;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;
import com.exe.board.user.SiteUser;

import lombok.RequiredArgsConstructor;


//생성자 오버로딩
@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
		
	public Page<Question> getLists(Pageable pageable){
		
		List<Sort.Order> sort = new ArrayList<Sort.Order>();
		sort.add(Sort.Order.desc("createdDate"));
		
		pageable = PageRequest.of(
				pageable.getPageNumber()<=0 ? 0 : 
					pageable.getPageNumber() -1 ,
					pageable.getPageSize(),Sort.by(sort));
		
		
		return questionRepository.findAll(pageable);
		
	}
	
	
	public Question getQuestion(Integer id) {
		
		Optional<Question> op = questionRepository.findById(id);
		
		if(op.isPresent())
			return op.get();
		else
			throw new DataNotFoundException("데이터 없슈");
		
		
	}
	
	public void create(String subject,String content,SiteUser author) {
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreatedDate(LocalDateTime.now());
		question.setAuthor(author);
		
		questionRepository.save(question);
		
	}
	
	
	public void modify(Question question, String subject, String content) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		
		questionRepository.save(question);
		
		
	}
	
	
	public void delete(Question question) {
		//delete는 내장되어있어서 바로 사용 가능
		questionRepository.delete(question);
	}
	
	
	
	public void vote(Question question, SiteUser siteUser) {
		
		question.getVoter().add(siteUser);
		
		questionRepository.save(question);
		
	}
	
	
	
	
	
	
}
