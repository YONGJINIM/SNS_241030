package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	// 토글: 좋아요/해제
	// input: postId , userId 
	// output: X
	// like를 조회(select) 후 postId와 userId가 있으면 삭제 없으면 추가
	// 분기문은 BO에서 진행 (삭제 추가 조회 쿼리문이 필요) 
	 public void toggleLike(int postId, int userId) {        
	        // 해당 게시물에 대해 사용자가 이미 좋아요를 눌렀는지 확인
	        int count = likeMapper.countLikeByPostIdAndUserId(postId, userId);
	        
	        // 로그 출력
	        //System.out.println("현재 좋아요 개수: " + count);

	        if (count > 0) {
	            // 이미 좋아요가 있는 경우 삭제
	        	//System.out.println("좋아요 삭제 중...");
	            likeMapper.deleteLikeByPostIdAndUserId(postId, userId);
	        } else {
	            // 좋아요가 없는 경우 추가
	        	//System.out.println("좋아요 추가 중...");
	            likeMapper.insertLike(postId, userId);
	        }
	    }
	 // 글에 해당하는 좋아요 갯수
	 // input: 글번호 
	 // output: 좋아요 갯수 
	 public int getLikeCountByPostId(
			 int postId) {
		 return likeMapper.selectLikeCountByPostId(postId);
	 }
	}
