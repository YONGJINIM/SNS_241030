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
		
	public void deleteLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
}
