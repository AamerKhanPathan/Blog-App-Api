package com.bikkadit.blog.services;

import java.util.List;

import com.bikkadit.blog.payloads.PostDto;
import com.bikkadit.blog.payloads.PostResponse;


public interface PostService {
	
	//create
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	
	String deletePost(Integer postId);
	
	//get all posts
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	
	//get post by category
	PostResponse getPostsByCategory(Integer categorId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	
	//get all posts by user
	PostResponse getPostsByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//search posts
	List<PostDto> searchPostsByTitle(String keyword);

	
	

}
