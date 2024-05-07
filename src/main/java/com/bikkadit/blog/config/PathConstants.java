package com.bikkadit.blog.config;

public class PathConstants {

	public static final String CATEGORY_URL = "/api/categories";

	public static final String CREATE_CATEGORY_URL = "/insertCategory";
//	public static final String GET_CATEGORY_URL = "/{categoryId}";
	public static final String GET_ALL_CATEGORY_URL = "/getAllCategories";
//	public static final String UPDATE_CATEGORY_URL = "/{categoryId}";
//	public static final String DELETE_CATEGORY_URL = "/{categoryId}";
//	
	public static final String CATEGORY_ID = "/{categoryId}";

	public static final String USER_URL = "api/users";

	public static final String CREATE_USER_URL = "/insertUser";
	public static final String GET_USER_URL = "user/{userId}";
	public static final String GET_ALL_USER_URL = "/allUsers";
	public static final String UPDATE_USER_URL = "update/{userId}";
	public static final String DELETE_USER_URL = "delete/{userId}";

	public static final String USER_ID = "/{userId}";

	public static final String API_URL = "/api";
	
	
	//post url constants
	public static final String CREATE_POST_URL = "/post/user/{userId}/category/{categoryId}";
	public static final String GET_POST_URL = "/post/{postId}";
	public static final String GET_POST_USER_URL = "/allUsers";
	public static final String UPDATE_POST_URL = "/post/update/{postId}";
	public static final String DELETE_POST_URL = "/post/delete/{postId}";
	public static final String GET_ALL_POST_URL = "/posts";
	public static final String GET_POST_BY_USER_URL="/post/user/{userId}";
	public static final String GET_POST_BY_CATEGORY_URL="/post/category/{categoryId}";
	
	public static final String SEARCH_POST_BY_TITLE_URL="/post/search/{keyword}";
	
	public static final String UPLOAD_IMAGE_URL="/post/image/upload/{postId}";
	public static final String SERVE_IMAGE_URL="/post/image/{imageName}";

	
	
	
	

}
