package com.exe.board.user;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public SiteUser create(String userName, String email, String password) {

		SiteUser user = new SiteUser();

		user.setUserName(userName);
		user.setEmail(email);

		//비밀번호는 암호화헤서 저장
		//Bcryp 해싱 함수
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		
		userRepository.save(user);
		
		return user;
		
		
	}
	
	
	public SiteUser getUser(String userName) {
		
		Optional<SiteUser> siteUser = userRepository.findByUserName(userName);
		
		if(siteUser.isPresent()) {
			
			return siteUser.get();
			
		}else {
			
			throw new DataNotFoundException("User Not Found");
			
		}
	}
	
	
}
