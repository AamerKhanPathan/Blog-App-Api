package com.bikkadit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.repositories.UserRepo;
import com.bikkadit.blog.services.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userdto) {
		User user = dtoToUser(userdto);

		User save = userRepo.save(user);

		return this.userToDto(save);
	}

	@Override
	public UserDto upadateUser(UserDto userDto, Integer userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", " id ", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User save = this.userRepo.save(user);

		return userToDto(save);
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user1 = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		return this.userToDto(user1);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();

//		List<UserDto> dtos=new ArrayList<UserDto>();
//		
//		for (User u: users) {
//			
//			dtos.add(u);
//			
//		}

		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		this.userRepo.delete(user);

	}

	private User dtoToUser(UserDto userDto) {

//		User user = new User();

//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		User user = this.modelMapper.map(userDto, User.class);


		return user;
	}

	private UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

}
