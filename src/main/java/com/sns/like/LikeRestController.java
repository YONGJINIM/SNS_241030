package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;


@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;

	// GET : /like?postId=3 	@RequestParm("postId")
	// GET: /like/3		/like/{postId} @PathVariable(name = "postId")

	@GetMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable(name = "postId") int postId, 
			HttpSession session){
		
		// 로그인 여부를 확인 로그인 안된사람을 튕겨냄 
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");				
		
		Map<String , Object> result = new HashMap<>();
				
		if(userLoginId == null) {
			result.put("code", 400);	
			result.put("error_message", "좋아요를 누르려면 로그인을 해주세요");
			return result; // 로그인이 안되어 있다면 아래로 갈 수 없음.
			
		}
		
		// toggle 요청 => BO					
		likeBO.toggleLike(postId, userId);
		
		
		result.put("code", 200);
		result.put("result", "성공");

		return result;
	}
}
