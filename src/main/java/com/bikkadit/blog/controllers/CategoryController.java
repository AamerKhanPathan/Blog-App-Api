package com.bikkadit.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.config.PathConstants;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.CategoryDto;
import com.bikkadit.blog.services.CategoryService;

import jakarta.validation.Valid;



@RestController
@RequestMapping(PathConstants.CATEGORY_URL)
public class CategoryController {
	
	

	@Autowired
	private CategoryService service;
	
	

	
	
	//Insert category
	
	/**
	 * @author Aamer khan
	 * @apiNote to insert a new category in database
	 * @param categoryDto
	 * @return
	 */
	
	@PostMapping(PathConstants.CREATE_CATEGORY_URL)
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto category = this.service.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.CREATED);
	}
	
	
	//update category
	
	/**
	 * @author Aamer khan
	 * @apiNote to update  category in database
	 * @param categoryDto
	 * @return
	 */
	
	@PutMapping(PathConstants.CATEGORY_ID)
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
	
		CategoryDto updateCategory = this.service.updateCategory(categoryDto, categoryId);
		
		
		
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
		

	}
	
	//delete
	
	/**
	 * @author Aamer khan
	 * @apiNote to delete  category from database
	 * @param categoryDto
	 * @return
	 */
	
	@DeleteMapping(PathConstants.CATEGORY_ID)
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		ApiResponse deleted = this.service.deleteCategory(categoryId);
		return  new ResponseEntity<ApiResponse>(deleted,HttpStatus.OK);
	}
	
	//get category
	
	/**
	 * @author Aamer khan
	 * @apiNote to fetch a  category  by category_id from database
	 * @param categoryDto
	 * @return
	 */
	
	@GetMapping(PathConstants.CATEGORY_ID)
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		CategoryDto category = this.service.getCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	//get all categories
	
	/**
	 * @author Aamer khan
	 * @apiNote to fetch all categories   from  database
	 * @param categoryDto
	 * @return
	 */
	@GetMapping(PathConstants.GET_ALL_CATEGORY_URL)
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		List<CategoryDto> allCategories = this.service.getAllCategories();
		
		
		return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.OK); 
	
		
	}
	
		
}
