package com.bikkadit.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bikkadit.blog.config.PathConstants;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.PostDto;
import com.bikkadit.blog.payloads.PostResponse;
import com.bikkadit.blog.services.FileService;
import com.bikkadit.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;

	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	// create post
	
	
	/**
	 * @author Aamer khan
	 * @apiNote to insert a new post in database
	 * @param PostDto
	 * @return
	 */

	@PostMapping(PathConstants.CREATE_POST_URL)
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	
	/**
	 * @author Aamer khan
	 * @apiNote to fetch post in database
	 * @param postDto
	 * @return
	 */
	// get post
	@GetMapping(PathConstants.GET_POST_URL)
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {

		PostDto postDto = postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

	// delete Post
	
	/**
	 * @author Aamer khan
	 * @apiNote to delete post from database
	 * @param postDto
	 * @return
	 */

	@DeleteMapping(PathConstants.DELETE_POST_URL)
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {

		String deletemsg = postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse(deletemsg, true), HttpStatus.OK);

	}

	// update posts
	
	/**
	 * @author Aamer khan
	 * @apiNote to update post in database
	 * @param userDto
	 * @return
	 */
	
	@PutMapping(PathConstants.UPDATE_POST_URL)
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @RequestBody PostDto postdto) {

		PostDto updatePost = postService.updatePost(postdto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	/**
	 * @author Aamer khan
	 * @apiNote to fetch all posts database
	 * @param userDto
	 * @return
	 */
	
	// get All post
	@GetMapping(PathConstants.GET_ALL_POST_URL)
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

		PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}


	// get post by user
	
	/**
	 * @author Aamer khan
	 * @apiNote to fetch all posts of user database
	 * @param userDto
	 * @return
	 */
	
	@GetMapping(PathConstants.GET_POST_BY_USER_URL)
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

		 PostResponse postResponse = postService.getPostsByUser(userId, pageNumber, pageSize, sortBy, sortDir);
		 

		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}

	// get post by category
	
	/**
	 * @author Aamer khan
	 * @apiNote to fetch all posts of category database
	 * @param userDto
	 * @return
	 */

	@GetMapping(PathConstants.GET_POST_BY_CATEGORY_URL)
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

		 PostResponse postResponse = postService.getPostsByCategory(categoryId,pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// search post by title
	
	/**
	 * @author Aamer khan
	 * @apiNote to search post by title in database
	 * @param userDto
	 * @return
	 */

	@GetMapping(PathConstants.SEARCH_POST_BY_TITLE_URL)
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
		List<PostDto> searchPosts = postService.searchPostsByTitle(keyword);
		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);

	}
	
	
	//post image upload
	
	
	/**
	 * @author Aamer khan
	 * @apiNote to upload image in post module
	 * @param userDto
	 * @return
	 */
	@PostMapping(PathConstants.UPLOAD_IMAGE_URL)
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
	
		String imageName = fileService.uploadImage(path, image);
		postDto.setPostImage(imageName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	//get image / serve files
	
	/**
	 * @author Aamer khan
	 * @apiNote to fetch image  from database
	 * @param userDto
	 * @return
	 */
	
	@GetMapping(value=PathConstants.SERVE_IMAGE_URL, produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable String imageName ,
			HttpServletResponse response
			) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
		
	}

}
