package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.comment.domain.Comment;

@Mapper
public interface CommentMapper {
	
	public int insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
	
	public List<Comment> selectCommentList(int postId);
	
	public List<Comment> selectCommentByPostId(int postId);
	
	public int deleteCommentById(int commentId);
}
