package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int countLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int selectLikeCountByPostId(int postId);
		
	// 두 메소드를 합친 하나의 쿼리 
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	public void deleteLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
}
