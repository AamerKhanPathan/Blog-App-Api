package com.bikkadit.blog.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Category;
import com.bikkadit.blog.entities.Post;
import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.PostDto;
import com.bikkadit.blog.payloads.PostResponse;
import com.bikkadit.blog.repositories.CategoryRepo;
import com.bikkadit.blog.repositories.PostRepo;
import com.bikkadit.blog.repositories.UserRepo;
import com.bikkadit.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CategoryRepo categoryRepo;

	ModelMapper mapper = new ModelMapper();

	// create/Insert post

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		Post post = mapper.map(postDto, Post.class);

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user ", "user_id", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category_id", categoryId));

		post.setPostTitle(postDto.getPostTitle());

		post.setPostContent(postDto.getPostContent());

		post.setPostImage("defaultimg.png");

		post.setAddedDate(new Date());

		post.setUser(user);

		post.setCategory(category);

		Post save = postRepo.save(post);

		return mapper.map(save, PostDto.class);
	}

	// update post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post_Id", postId));

		post.setPostTitle(postDto.getPostTitle());

		post.setPostContent(postDto.getPostContent());
		
		if (!(postDto.getPostImage()==null)) {
			post.setPostImage(postDto.getPostImage());
		}
		

		Post save = postRepo.save(post);

		return this.mapper.map(save, PostDto.class);
	}

	// delete post By Id

	@Override
	public String deletePost(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post_Id", postId));
		postRepo.delete(post);
		return "post deleted successfully";
	}

	// find all posts

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		
		//ternary operator
		//Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Sort sortProper=null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sortProper=Sort.by(sortBy).ascending();
			
		} else if (sortDir.equalsIgnoreCase("des")) {
			sortProper=Sort.by(sortBy).descending();
		}
		else {
			//if unexpected sorting direction
			sortProper=Sort.by(sortBy).ascending();
		}
		

		Pageable pageable = PageRequest.of(pageNumber,pageSize,sortProper);

		Page<Post> page = postRepo.findAll(pageable);

		List<Post> posts = page.getContent();

		List<PostDto> postDtos = new ArrayList<>();

		for (Post post : posts) {

			PostDto map = mapper.map(post, PostDto.class);
			postDtos.add(map);
		}

		PostResponse postResponse = new PostResponse();

		postResponse.setContents(postDtos);
		postResponse.setPageNumber(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElement(page.getTotalElements());
		postResponse.setTatalPages(page.getTotalPages());
		postResponse.setLastPage(page.isLast());

		return postResponse;
	}

	// find post by id

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post_Id", postId));

		return this.mapper.map(post, PostDto.class);
	}

	// find posts by category

	@Override
	public PostResponse getPostsByCategory(Integer categorId,Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		
		
		Category category = categoryRepo.findById(categorId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category_id", categorId));
		
		Sort sort=sortDir.equalsIgnoreCase("des")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> page = postRepo.findAllByCategory(category, pageable);
		
		List<Post> posts = page.getContent();
		
		

		List<PostDto> postDtos = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse  postResponse=new PostResponse();
		
		postResponse.setContents(postDtos);
		postResponse.setPageNumber(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElement(page.getTotalElements());
		postResponse.setTatalPages(page.getTotalPages());
		postResponse.setLastPage(page.isLast());

		return postResponse;
	}

	// find posts by user
	@Override
	public PostResponse getPostsByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user ", "userId", userId));
		
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
       
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> page = postRepo.findAllByUser(user,pageable);
		
		
		List<Post> posts = page.getContent();		

		List<PostDto> postDtos = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContents(postDtos);
		postResponse.setPageNumber(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElement(page.getTotalElements());
		postResponse.setTatalPages(page.getTotalPages());
		postResponse.setLastPage(page.isLast());

		
		
		
		

		return postResponse;
	}

	//search post by title
	@Override
	public List<PostDto> searchPostsByTitle(String keyword) {
		List<Post> posts = postRepo.findByPostTitleContaining(keyword);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
