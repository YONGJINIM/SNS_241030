package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String , Object> create(
			@RequestParam(value = "content" , required = false) String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		
	Integer userId = (Integer) session.getAttribute("userId");
	String userLoginId = (String) session.getAttribute("userLoginId");

		
		// 로그인 여부를 확인해야 해서 응답을 빠르게 내려야 함. 
		Map<String , Object> result = new HashMap<>();
		
		if(userId == null) {
		result.put("code", 400);	
		result.put("error_message", "로그인을 해주세요");
		return result; // 로그인이 안되어 있다면 아래로 갈 수 없음.
		} 
	
		// db insert
		postBO.addPost(userId, userLoginId, content, file);
		
		// 성공 
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	@DeleteMapping("/delete")
	public Map<String , Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session){
			
			// userId  
			int userId = (int)session.getAttribute("userId");
								
			// 응답
			Map<String , Object> result = new HashMap<>();
			result.put("code", 200);
			result.put("result", "성공");
			return result;
	}
  }
