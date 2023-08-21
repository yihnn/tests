package com.exe.board.answer;

import org.springframework.data.jpa.repository.JpaRepository;

//<Entity Type, Primary Key Type>
public interface AnswerRepository extends JpaRepository<Answer, Integer>{

}
