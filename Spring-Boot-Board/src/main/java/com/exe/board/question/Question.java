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
//@Id : primary key(�ߺ��� X)

@Getter
@Setter
@Entity	//JPA�� Entity�� �ν�
public class Question {

	//��ƼƼ �����(dto�� �ʿ��� ������ Entity�� ����� �������)
	@Id
	//+1�� �Ǵ� ���� �ڵ����� ��(ex:sequence)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//���̺� ������ ���� �÷� ����
	@Column(length = 200)
	private String subject;
	
	//�ؽ�Ʈ�� ��� ������ �÷�
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createdDate;
	
	//������ �ϳ��ε� ���� ������->List�� ����
	//Answer�� �������
	//cascade = CascadeType.REMOVE : ����(����) ������ ���� �䵵 �� ����
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
			//fetch = FetchType.EAGER)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	//���ƿ�, ��õ�� �δ� �� ���� �����ϱ� ������ �ߺ��� �ȵǰ� set ���
	//���̺��� ������ �����ǰ�, Question_id�� FK, Vorter_id�� ����
	@ManyToMany
	Set<SiteUser> voter;
	
	
	
}
