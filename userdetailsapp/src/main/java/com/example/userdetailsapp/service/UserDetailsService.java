package com.example.userdetailsapp.service;

import org.springframework.stereotype.Service;

import com.example.userdetailsapp.dto.UserDetailsDto;

@Service
public interface UserDetailsService {
	
	public Object addUser(UserDetailsDto userDetailsDto);
	public Object deleteUser(Long userId);
	public Object getAllUserDetails();
	public Object getSpecificUserDetails(Long userId);
	public Object updateUser(UserDetailsDto userDetailsDto);


}
