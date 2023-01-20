package com.example.userdetailsapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.userdetailsapp.dto.UserDetailsDto;
import com.example.userdetailsapp.exception.UserException;
import com.example.userdetailsapp.service.UserDetailsService;

@RestController
public class UserDetailsController {

	Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping(value = "/v1/addUserDetails")
	public ResponseEntity<Object> addUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
		logger.info("Request for addUserDetails :{}", userDetailsDto);
		validateRequest(userDetailsDto);
		return new ResponseEntity<Object>(userDetailsService.addUser(userDetailsDto), HttpStatus.OK);
	}

	@GetMapping(value = "/v1/getSpecificUserDetails")
	public ResponseEntity<Object> getSpecificUserDetail(@RequestParam Long userId) {
		if (userId == null) {
			throw new UserException("Kindly provide userID");
		}
		logger.info("Request for getSpecificUserDetail userId :{}", userId);
		return new ResponseEntity<Object>(userDetailsService.getSpecificUserDetails(userId), HttpStatus.OK);
	}

	@GetMapping(value = "/v1/getAllUsersDetails")
	public ResponseEntity<Object> getAllUserDetails() {
		logger.info("Request for getAllUserDetails");
		return new ResponseEntity<Object>(userDetailsService.getAllUserDetails(), HttpStatus.OK);
	}

	@PutMapping(value = "/v1/updateUserDetails")
	public ResponseEntity<Object> updateUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
		logger.info("Request for updateUserDetails :{}", userDetailsDto);
		return new ResponseEntity<Object>(userDetailsService.updateUser(userDetailsDto), HttpStatus.OK);
	}

	@DeleteMapping(value = "/v1/deleteUserDetails")
	public ResponseEntity<Object> deleteUserDetails(@RequestParam Long userId) {
		if (userId == null) {
			throw new UserException("Kindly provide userID");
		}
		logger.info("Request for deleteUserDetails :{}", userId);
		return new ResponseEntity<Object>(userDetailsService.deleteUser(userId), HttpStatus.OK);
	}

	public void validateRequest(UserDetailsDto userDetailsDto) {
		if (userDetailsDto.getAddress() == null || userDetailsDto.getName() == null
				|| userDetailsDto.getCompany() == null || userDetailsDto.getEmail() == null
				|| userDetailsDto.getAddress().getGeo() == null || userDetailsDto.getPhone() == null
				|| userDetailsDto.getUserName() == null || userDetailsDto.getCompany().getLocation() == null
				|| userDetailsDto.getCompany().getName() == null
				|| userDetailsDto.getAddress().getGeo().getLat() == null
				|| userDetailsDto.getAddress().getGeo().getLongitude() == null) {
			throw new UserException("Invalid Request Data");
		}
	}

}
