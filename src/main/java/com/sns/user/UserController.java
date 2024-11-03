package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 회원 가입화면
	 * @return
	 */
	@GetMapping("/sign-up-view")
	public String signUpView() {
		return "user/signUp";
	}
	/**
	 * 로그인 화면
	 * @return
	 */
	@GetMapping("/sign-in-view")
	public String signInView() {
		return "user/signIn";
	}
}
