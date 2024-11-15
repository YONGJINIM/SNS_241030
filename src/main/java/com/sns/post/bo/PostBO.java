package com.sns.post.bo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@lombok.extern.slf4j.Slf4j
@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private LikeBO likeBO;

	@Autowired
	private CommentBO commentBO;
	
    @Autowired
    private FileManagerService fileManagerService;

	// input:X
	// output:List<PostEntity>
	public List<PostEntity> getPostList() {
		return postRepository.findByOrderByIdDesc();
	}
	
    public PostEntity addPost(
    		int userId, 
    		String userLoginId, 
    		String content, 
    		MultipartFile file) {
    		
    String imagePath = fileManagerService.uploadFile(file, userLoginId);
   
    	return postRepository.save(
    			PostEntity.builder()
    			.userId(userId)
    			.content(content)
    			.imagePath(imagePath)
    			.build());
  
    }
    @Transactional
    public void deletePostByPostId(int postId, int userId) {
        // 삭제할 글이 있는지 확인
        PostEntity post = postRepository.findByIdAndUserId(postId, userId).orElse(null);
        if (post == null) {
            log.info("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
            return;
        }

        // 댓글 삭제
        commentBO.deleteCommentByPostId(postId);

        // 좋아요 삭제
        likeBO.deleteLikePostId(postId);

        // 글 삭제
        postRepository.delete(post);

        // 기존 글에 이미지가 있다면 폴더/파일 삭제
        if (post.getImagePath() != null) {
            fileManagerService.deleteFile(post.getImagePath());
        }
    }
}
