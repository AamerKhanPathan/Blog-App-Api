package com.bikkadit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikkadit.blog.entities.Comment;

public interface CommentRepo  extends JpaRepository<Comment , Integer>{
	

}
