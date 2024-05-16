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

import com.bikkadit.blog.config.ApiConstants;
import com.bikkadit.blog.config.PathConstants;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.services.UserServiceI;

import jakarta.validation.Valid;
  
@RestController
@RequestMapping(PathConstants.USER_URL)
public class UserController {
	
	@Autowired
	private UserServiceI userServiceI;
		
	
	//Post -create user 
	
	
	/**
	 * @author Aamer khan
	 * @apiNote to insert a new user in database
	 * @param UserDto
	 * @return UserDto
	 */
	
	@PostMapping(PathConstants.CREATE_USER_URL)
	public ResponseEntity<UserDto> createUser( @Valid  @RequestBody UserDto userDto){
		UserDto user = this.userServiceI
				.createUser(userDto);
		
		return new ResponseEntity<UserDto>(user,HttpStatus.CREATED);
		
		
	}
	
	//Get - fetch user by id
	
	/**
	 * @author Aamer khan
	 * @apiNote to fetch a  user from database
	 * @param Integer 
	 * @return UserDto
	 */
	
	@GetMapping(PathConstants.USER_ID)
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uId){
		
		UserDto userById = this.userServiceI.getUserById(uId);
		
		
		
		return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
	}
	
	
	//GET - get user
	
	/**
	 * @author Aamer khan
	 * @apiNote to fetch all  users from database
	 * @param 
	 * @return List<UserDto>
	 */
	
	@GetMapping(PathConstants.GET_ALL_USER_URL)
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		 List<UserDto> allUsers = this.userServiceI.getAllUsers();
		
		
		
		
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
	}
	
	
	
	
	//put- update user
	
	
	/**
	 * @author Aamer khan
	 * @apiNote to update a  user in database
	 * @param userDto
	 * @return
	 */
	@PutMapping(PathConstants.USER_ID)
	public ResponseEntity<UserDto> upadateUser(@RequestBody UserDto userdto,@PathVariable Integer userId){
		
		UserDto upadateUser = this.userServiceI.upadateUser(userdto, userId);
		
		
		
		//return new ResponseEntity<UserDto>(upadateUser, HttpStatus.OK);
		
		return ResponseEntity.ok(upadateUser);
		
	}
	
	
	//DELETE- for delete user
	
	/**
	 * @author Aamer khan
	 * @apiNote to remove/delete a  user from database
	 * @param userDto
	 * @return
	 */
	
	
	@DeleteMapping(PathConstants.USER_ID)
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userServiceI.deleteUser(userId);
		
		//return new ResponseEntity(Map.of("msg","successfully deleted"), HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstants.USER_DELETE_MSG, true),HttpStatus.OK);
	}
	
	
	

}
