package com.exe.board.question;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//<Entity Type, Primary Key Type>
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	//사용자가 정의한 메소드는 여기에 만들기
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject,String content);
	
	List<Question> findBySubjectLike(String subject);
	
	Page<Question> findAll(Pageable pageable);
}


/*
데이터 처리를 위해서는
실제 데이터 베이스와 연동하는 JPA Repository가 필요

Entity에 의해 생성된 테이블에 접근하는 메소드(findAll,save,findById)를
사용하기 위한 인터페이스

기본 메소드 : save , update(save), delete, findById, findAll
사용자 정의 메소드 : findBySubject, findBySubjectAndContent, findBySubjectLike, ...
CRUD(Create,Read,Update,Delete)를 어떻게 처리할지 정의하는 계층이 Repository

*/
