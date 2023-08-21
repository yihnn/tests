package com.exe.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.FetchType;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exe.board.answer.AnswerRepository;
import com.exe.board.question.QuestionRepository;
import com.exe.board.question.QuestionService;

@SpringBootTest
class SpringBootBoardApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void contextLoads() {
		
		
	}
	
	/*
	@Test
	void save() {

		Question q1 = new Question();
		q1.setSubject("스프링 부트가 뭐게에~");
		q1.setContent("스프링 부트에 대해 알고싶뉭");
		q1.setCreatedDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("JPA가 뭐게에~2");
		q2.setContent("JPA란 말야...2");
		q2.setCreatedDate(LocalDateTime.now());
		this.questionRepository.save(q2);
		
	}
	*/
	
	
	/*
	@Test
	void findAll() {
		
		List<Question> lists = questionRepository.findAll();
		
		//Question 찾아서 불러왔을 때 size가 2개인지 묻는 실행문
		assertEquals(2, lists.size());
		
		//0번째 제목을 불러와서 같은지 실행
		Question q = lists.get(0);
		assertEquals("스프링 부트가 뭐게에~", q.getSubject());
	}
	
	*/
	
	/*
	@Test
	void findBySubject() {
		
		Question q =  questionRepository.findBySubject("JPA가 뭐게에~2");
		//검사해서 나온 id 갯수 일치 여부 확인
		assertEquals(4, q.getId());
		
	}
	*/
	
	
	/*
	@Test
	void findBySubjectAndContent() {
	
		Question q = questionRepository.findBySubjectAndContent(
				"JPA가 뭐게에~2","JPA란 말야...2");
				
		assertEquals(4, q.getId());		
	}
	*/
	
	/*
	@Test
	void findBySubjectLike() {
		
		List<Question> lists = questionRepository.findBySubjectLike("스프링%");
		
		
		Question q = lists.get(1);
		
		assertEquals("스프링 부트가 뭐게에~", q.getSubject());
		
		
	}
	*/
	
	/*
	@Test
	void update() {
	      
	      //optional은 결과를 받은 클라이언트에게
	      //null처리를 의무화해서 코드를 안전하게 유도
	      //select했는데 데이터가 없어도 에러가 나지 않는다.
	      Optional<Question> op = questionRepository.findById(2);
	      assertTrue(op.isPresent());
	      
	      //수정할 데이터 입력
	      Question q = op.get();
	      q.setSubject("스프링2");
	      q.setContent("스프링부트2");
	      
	      //수정
	      questionRepository.save(q);
	      
	   }
	   
	   */
	
	
	   /*
	   
	   @Test
	   void delete() {
	      
	      //현재 데이터 개수확인
	      assertEquals(4, questionRepository.count());
	      
	      Optional<Question> op = questionRepository.findById(3);
	      
	      //꺼낸 데이터가 있는지 확인!
	      assertTrue(op.isPresent());
	      
	      Question q = op.get();
	      
	      //삭제
	      questionRepository.delete(q);
	      
	      //삭제 후 데이터 개수 확인
	      //삭제 제대로 됐는지 확인
	      assertEquals(3, questionRepository.count());
	      
	   }
	   
	*/
	
	
	//Answer
	   /*
	   @Test
	   void replySave() {
		   
		   Optional<Question> op = questionRepository.findById(2);
		   
		   assertTrue(op.isPresent());
		   
		   Question q = op.get();
		   
		   Answer a = new Answer();
		   a.setContent("JPA는 ORM이다");
		   a.setQuestion(q);
		   a.setCreatedDate(LocalDateTime.now());
		   
		   answerRepository.save(a);
		   
		   
	   }
	   */
	
	/*
	@Test
	void replyFin() {
		
		Optional<Answer> op = answerRepository.findById(1);
		
		assertTrue(op.isPresent());
		
		Answer a = op.get();
		
		assertEquals(2, a.getQuestion().getId());
		//assertEquals("스프링2", a.getQuestion().getSubject());
	}
	*/
	
	
	/*
	//한 번에 무조건 다 실행시켜라(fetch = FetchType.EAGER로 해도 됨)
	@Transactional	
	@Test
	void replyConnectQuestion() {
		
		Optional<Question> op = questionRepository.findById(2);
		assertTrue(op.isPresent());
		
		Question q = op.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("JPA는 ORM이다", answerList.get(0).getContent());
		
	}
	
	*/
	
	/*
	@Test
	void save300() {
		
		for(int i=1; i<=300; i++) {
			
			String subject = String.format("테스트 데이터여:[%03d]", i);
			String content = String.format("스프링부트여:[%03d]", i);
			
			questionService.create(subject, content,null);
		}
	}
	*/
}
