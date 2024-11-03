package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	
	/**
	 * 아이디 중복 확인 
	 * @param loginId
	 * @return
	 */
	@GetMapping("/is-duplicate-id")
	public Map<String , Object> isDuplicateId (
			@RequestParam("loginId") String loginId) {
		
		// db select (브레이크 포인트 걸어서 확인)
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		boolean isDuplicate = false; // 중복이 아님
		
		if(user != null) { // user이 null이 아닐 때 행이 존재함 
			isDuplicate = true;
		}
		
		// 응답 값
		Map<String, Object> result = new HashMap<>(); // 브레이크 포인트 
		result.put("code", 200);
		result.put("is_duplicate_id", false);
		return result;
		
	}
		/**
		 * 회원가입 API
		 * @param loginId
		 * @param password
		 * @param name
		 * @param email
		 * @return
		 */
		@PostMapping("/sign-up")
		public Map<String, Object> signUp(
				@RequestParam("loginId") String loginId,
				@RequestParam("password") String password,
				@RequestParam("name") String name,
				@RequestParam("email") String email){
			
			// 암호화 알고리즘 적용 - 해싱알고리즘 적용
			// pw:aaaa -> 74b8733745420d4d33f80c4663dc5e5
			// pw:bbbb -> 74b8733745420d4d33f80c4663dc5e5
			// common 패키지 생성 후 EncryptUtils 클래스 생성 
			String hashedPassword = EncryptUtils.md5(password);
			
			// db insert
			 UserEntity user = userBO.addUser(loginId, hashedPassword, name, email);
			
			// 응답 값 
			Map<String, Object> result = new HashMap<>(); // 브레이크 포인트로 확인
			if(user != null) {
				result.put("code", 200);
				result.put("result", "성공");
			}else {
				result.put("code", 500);
				result.put("error_message", "회원 가입에 실패하였습니다.");
			}
			
			return result;
		}
		@PostMapping("/sign-in")
		public Map<String, Object> signIn(
				@RequestParam("loginId") String loginId,
				@RequestParam("password") String password,
				HttpServletRequest request){
			
		// db insert 브레이크 포인트 걸어서 확인
		UserEntity user =  userBO.getUserEntityByLoginIdPassword(loginId, password);
			
		Map<String, Object> result = new HashMap<>(); // 브레이크 포인트로 확인
		
		if(user != null) {
			// 세션에 사용자 정보를 넣는다. (사용자 각각의) 
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "존재하지 않는 사용자 입니다.");
		}
		return result;
		}
		
}
