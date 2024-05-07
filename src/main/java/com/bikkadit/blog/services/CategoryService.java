package com.bikkadit.blog.services;

import java.util.List;

import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.CategoryDto;

public interface CategoryService {
	
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	ApiResponse deleteCategory(Integer categoryId);
	
	//get
	
	CategoryDto getCategory(Integer categoryId);
	
	
	//getAll
	List<CategoryDto> getAllCategories();
	
	

}
