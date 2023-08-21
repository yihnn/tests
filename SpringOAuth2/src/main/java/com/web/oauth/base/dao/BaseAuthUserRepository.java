package com.web.oauth.base.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.oauth.base.model.BaseAuthUser;



public interface BaseAuthUserRepository 
	extends JpaRepository<BaseAuthUser, Long>{

	//email�� ���� �̹� ������ ����� or ó�� �����ϴ� ���������
	//Ȯ���ϴ� �޼ҵ�
	Optional<BaseAuthUser> findByEmail(String email);
	
	
	
	
	
}
