package com.exe.board.question;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.exe.board.answer.Answer;
import com.exe.board.user.SiteUser;

import lombok.Getter;
import lombok.Setter;


//@Entity : JPA
//@Getter,@Setter: lombok
//@Id : primary key(중복값 X)

@Getter
@Setter
@Entity	//JPA가 Entity로 인식
public class Question {

	//엔티티 만들기(dto에 필요한 변수를 Entity로 만들면 만들어짐)
	@Id
	//+1씩 되는 값이 자동으로 들어감(ex:sequence)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//테이블 형성을 위한 컬럼 생성
	@Column(length = 200)
	private String subject;
	
	//텍스트만 사용 가능한 컬럼
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createdDate;
	
	//질문이 하나인데 답이 여러개->List에 담음
	//Answer와 상관관계
	//cascade = CascadeType.REMOVE : 상위(질문) 삭제면 하위 답도 다 삭제
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
			//fetch = FetchType.EAGER)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	//좋아요, 추천은 인당 한 번만 가능하기 때문에 중복이 안되게 set 사용
	//테이블은 개별로 생성되고, Question_id는 FK, Vorter_id는 개별
	@ManyToMany
	Set<SiteUser> voter;
	
	
	
}
