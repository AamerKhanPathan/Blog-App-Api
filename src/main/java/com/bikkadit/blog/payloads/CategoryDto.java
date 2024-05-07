package com.bikkadit.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CategoryDto {

	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 3,message = "Title must be minimum 3 charectors")
	private String categoryTitle;
	
	
	@NotBlank
	@Size(min = 10,max=1000,message = "Description must be between  10-1000 charectors")
	private String categoryDescription;
}
