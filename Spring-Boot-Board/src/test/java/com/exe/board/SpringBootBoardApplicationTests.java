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
		q1.setSubject("������ ��Ʈ�� ���Կ�~");
		q1.setContent("������ ��Ʈ�� ���� �˰�͇�");
		q1.setCreatedDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("JPA�� ���Կ�~2");
		q2.setContent("JPA�� ����...2");
		q2.setCreatedDate(LocalDateTime.now());
		this.questionRepository.save(q2);
		
	}
	*/
	
	
	/*
	@Test
	void findAll() {
		
		List<Question> lists = questionRepository.findAll();
		
		//Question ã�Ƽ� �ҷ����� �� size�� 2������ ���� ���๮
		assertEquals(2, lists.size());
		
		//0��° ������ �ҷ��ͼ� ������ ����
		Question q = lists.get(0);
		assertEquals("������ ��Ʈ�� ���Կ�~", q.getSubject());
	}
	
	*/
	
	/*
	@Test
	void findBySubject() {
		
		Question q =  questionRepository.findBySubject("JPA�� ���Կ�~2");
		//�˻��ؼ� ���� id ���� ��ġ ���� Ȯ��
		assertEquals(4, q.getId());
		
	}
	*/
	
	
	/*
	@Test
	void findBySubjectAndContent() {
	
		Question q = questionRepository.findBySubjectAndContent(
				"JPA�� ���Կ�~2","JPA�� ����...2");
				
		assertEquals(4, q.getId());		
	}
	*/
	
	/*
	@Test
	void findBySubjectLike() {
		
		List<Question> lists = questionRepository.findBySubjectLike("������%");
		
		
		Question q = lists.get(1);
		
		assertEquals("������ ��Ʈ�� ���Կ�~", q.getSubject());
		
		
	}
	*/
	
	/*
	@Test
	void update() {
	      
	      //optional�� ����� ���� Ŭ���̾�Ʈ����
	      //nulló���� �ǹ�ȭ�ؼ� �ڵ带 �����ϰ� ����
	      //select�ߴµ� �����Ͱ� ��� ������ ���� �ʴ´�.
	      Optional<Question> op = questionRepository.findById(2);
	      assertTrue(op.isPresent());
	      
	      //������ ������ �Է�
	      Question q = op.get();
	      q.setSubject("������2");
	      q.setContent("��������Ʈ2");
	      
	      //����
	      questionRepository.save(q);
	      
	   }
	   
	   */
	
	
	   /*
	   
	   @Test
	   void delete() {
	      
	      //���� ������ ����Ȯ��
	      assertEquals(4, questionRepository.count());
	      
	      Optional<Question> op = questionRepository.findById(3);
	      
	      //���� �����Ͱ� �ִ��� Ȯ��!
	      assertTrue(op.isPresent());
	      
	      Question q = op.get();
	      
	      //����
	      questionRepository.delete(q);
	      
	      //���� �� ������ ���� Ȯ��
	      //���� ����� �ƴ��� Ȯ��
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
		   a.setContent("JPA�� ORM�̴�");
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
		//assertEquals("������2", a.getQuestion().getSubject());
	}
	*/
	
	
	/*
	//�� ���� ������ �� ������Ѷ�(fetch = FetchType.EAGER�� �ص� ��)
	@Transactional	
	@Test
	void replyConnectQuestion() {
		
		Optional<Question> op = questionRepository.findById(2);
		assertTrue(op.isPresent());
		
		Question q = op.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("JPA�� ORM�̴�", answerList.get(0).getContent());
		
	}
	
	*/
	
	/*
	@Test
	void save300() {
		
		for(int i=1; i<=300; i++) {
			
			String subject = String.format("�׽�Ʈ �����Ϳ�:[%03d]", i);
			String content = String.format("��������Ʈ��:[%03d]", i);
			
			questionService.create(subject, content,null);
		}
	}
	*/
}
