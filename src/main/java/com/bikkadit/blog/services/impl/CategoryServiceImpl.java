package com.bikkadit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Category;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.CategoryDto;
import com.bikkadit.blog.repositories.CategoryRepo;
import com.bikkadit.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper mapper;

	// create category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub

		Category category = this.mapper.map(categoryDto, Category.class);

		Category addedCat = this.categoryRepo.save(category);

		return this.mapper.map(addedCat, CategoryDto.class);
	}

	// update category
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", " id", categoryId));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());

		Category saved = this.categoryRepo.save(category);

		return this.mapper.map(saved, CategoryDto.class);
	}

	@Override
	public ApiResponse deleteCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));

		this.categoryRepo.delete(category);

		return new ApiResponse("successfully deleted", true);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category ", "id", categoryId));

		CategoryDto categoryDto = this.mapper.map(category, CategoryDto.class);

		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {

		List<Category> categories = this.categoryRepo.findAll();

		List<CategoryDto> categoryDtos = categories.stream().map((cat) -> this.mapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return categoryDtos;
	}

}
