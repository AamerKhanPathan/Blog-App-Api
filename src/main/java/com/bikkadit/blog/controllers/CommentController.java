package com.bikkadit.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.CommentDto;
import com.bikkadit.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/comment/post/{postId}/user/{userId}")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId, @PathVariable Integer userId) {
		CommentDto addComment = this.commentService.addComment(commentDto, postId,userId);

		return new ResponseEntity<CommentDto>(addComment, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/comment/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		ApiResponse deleted = this.commentService.deleteComment(commentId);
		return  new ResponseEntity<ApiResponse>(deleted,HttpStatus.OK);
		
	}

}
