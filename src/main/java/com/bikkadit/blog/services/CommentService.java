package com.bikkadit.blog.services;

import com.bikkadit.blog.entities.Comment;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.CommentDto;

public interface CommentService  {
	
	
	public CommentDto addComment(CommentDto comment,Integer postId,Integer userId );
	
	
	public ApiResponse deleteComment(Integer commentId);
	
	public Comment updateComment(CommentDto comment,CommentDto commentId);
	
	public Comment getComment(CommentDto commentId);
	

}
