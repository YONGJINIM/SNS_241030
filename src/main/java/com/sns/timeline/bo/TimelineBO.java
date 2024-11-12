package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TimelineBO {
	
	
	private final PostBO postBO;
	private final UserBO userBO;
	private final CommentBO commentBO;
	private final LikeBO likeBO;
	
	// input : X
	// output : List<CardDTO>
	// TimelineBO 클래스의 generateCardList 메서드 수정
	public List<CardDTO> generateCardList() {
	    List<CardDTO> cardList = new ArrayList<>();
	    
	    // 글 목록을 가져옴
	    List<PostEntity> postList = postBO.getPostList();
	    
	    // 글 1개 => CardDTO로 변환 반복문을 사용
	    for (PostEntity postEntity : postList) {            
	        CardDTO card = new CardDTO();
	        
	        // 글 설정
	        card.setPost(postEntity);
	        
	        // 글쓴이 설정
	        card.setUser(userBO.getUserEntityById(postEntity.getUserId()));
	        
	        // 댓글들
	        card.setCommentList(commentBO.generateCommentList(postEntity.getId()));
	       
	        // 좋아요 갯수
	        card.setLikeCount(likeBO.getLikeCountByPostId(postEntity.getId()));
	        
	        
	        // cardList에 추가
	        cardList.add(card);
	    }
	    
	    return cardList;
	}
}
