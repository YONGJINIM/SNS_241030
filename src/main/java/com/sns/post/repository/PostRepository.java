package com.sns.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	public List<PostEntity> findByOrderByIdDesc();

	public Optional<PostEntity> findByIdAndUserId(int postId , int userId);
	
	public int deleteById(int postId);
}