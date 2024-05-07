package com.bikkadit.blog.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	
private Integer commentId;
	
	
	private String content;
	
	
	private PostDto post;
	
	private UserDto user;
	
	
	

}
