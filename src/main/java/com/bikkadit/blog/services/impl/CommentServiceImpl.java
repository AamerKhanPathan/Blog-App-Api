package com.bikkadit.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Comment;
import com.bikkadit.blog.entities.Post;
import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.CommentDto;
import com.bikkadit.blog.repositories.CommentRepo;
import com.bikkadit.blog.repositories.PostRepo;
import com.bikkadit.blog.repositories.UserRepo;
import com.bikkadit.blog.services.CommentService;

@Service 
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	ModelMapper mapper;
	
	
	@Override
	public CommentDto addComment(CommentDto commentDto, Integer postId ,Integer userId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));
		
	    Comment comment = this.mapper.map(commentDto, Comment.class);
	    
	    comment.setPost(post);
	    comment.setUser(user);
	    
	    Comment savedComment = commentRepo.save(comment);
	    
	    
	    
	   
	    
		
		
		
		return this.mapper.map(savedComment, CommentDto.class);
	}

	

	@Override
	public ApiResponse deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "comment ID", commentId));
		
		commentRepo.delete(comment);
		
		return new ApiResponse("comment deleted successfully",true); 
	}

	@Override
	public Comment updateComment(CommentDto comment, CommentDto commentId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Comment getComment(CommentDto commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
