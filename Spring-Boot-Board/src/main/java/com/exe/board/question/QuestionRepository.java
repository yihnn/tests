package com.exe.board.question;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//<Entity Type, Primary Key Type>
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	//����ڰ� ������ �޼ҵ�� ���⿡ �����
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject,String content);
	
	List<Question> findBySubjectLike(String subject);
	
	Page<Question> findAll(Pageable pageable);
}


/*
������ ó���� ���ؼ���
���� ������ ���̽��� �����ϴ� JPA Repository�� �ʿ�

Entity�� ���� ������ ���̺� �����ϴ� �޼ҵ�(findAll,save,findById)��
����ϱ� ���� �������̽�

�⺻ �޼ҵ� : save , update(save), delete, findById, findAll
����� ���� �޼ҵ� : findBySubject, findBySubjectAndContent, findBySubjectLike, ...
CRUD(Create,Read,Update,Delete)�� ��� ó������ �����ϴ� ������ Repository

*/
