package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

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
	
	@GetMapping("/sign-out")
	public String signOut(HttpSession session) { 
		// session을 비운다. 
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		// 로그인 페이지로 이동(redirect)
		return "redirect:/user/sign-up-view";
	}
}
