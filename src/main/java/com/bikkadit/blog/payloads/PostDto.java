package com.bikkadit.blog.payloads;





import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private Integer postId;
	
	private String postTitle;
	
	private String postContent;
	
	private String postImage;
	
	private UserDto user;
	
	private CategoryDto category;
	
	
	
	


}
