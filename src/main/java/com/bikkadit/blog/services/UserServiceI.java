package com.bikkadit.blog.services;

import java.util.List;

import com.bikkadit.blog.payloads.UserDto;

public interface UserServiceI {
	
	UserDto createUser(UserDto user);
	
	UserDto upadateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List< UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}

