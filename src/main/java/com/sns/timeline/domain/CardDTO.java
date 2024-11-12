package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentDTO;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

// 글 1개 => Card 1개
@Getter
@Setter
public class CardDTO {
	// 글 1개
	private PostEntity post;
	
	// 글쓴이 정보
	private UserEntity user;
		
	// 댓글 N개
	private List<CommentDTO> commentList;
	
	// 좋아요 N개 
	private int likeCount;
	
	// 좋아요 여부 
	private boolean filledLike;
	
}
