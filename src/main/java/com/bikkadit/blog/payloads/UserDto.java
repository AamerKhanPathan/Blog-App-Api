package com.bikkadit.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private Integer id;

	// @NotNull
	@NotEmpty
	@Size(min = 2, message = "name must not less than 2 charectors")
	private String name;

	@Email
	
	private String email;

	@NotEmpty
	@Size(min = 6, message = "pass must not less than 6 charectors")
	private String password;

	
	private String about;

}
