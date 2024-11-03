package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.common.EncryptUtils;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	// 컨트롤러한테 in / output 
	// input : loginId
	// output : UserEntity or null (단건 널이거나 존재하거나) 
	
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	// input : loginId, password, name, email  
	// output :  UserEntity
	public UserEntity addUser(String loginId, String password,
			String name, String email) {
		// 실질적인 오류 발생시 null return이 안됨 (try-catch로 예외처리 해야함)
		return userRepository.save(
				UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build());			
	}
	
	// input : loginId, password
	// output : UserEntity
	public UserEntity getUserEntityByLoginIdPassword(
			String loginId,
			String password) {
		
		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		// 조회 
		return userRepository.findByLoginIdAndPassword(loginId , hashedPassword);
	}
	
	
	
	
}
