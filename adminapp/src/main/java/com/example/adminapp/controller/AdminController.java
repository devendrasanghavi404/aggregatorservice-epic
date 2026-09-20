package com.example.adminapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.adminapp.service.AdminService;

@RestController
public class AdminController {
	

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;

	@GetMapping(value = "/v1//getUserPostById")
	public ResponseEntity<Object> getUserPostById(@RequestParam String userId) {
		logger.info("Inside getUserPostById of AdminController");
		return new ResponseEntity<Object>(adminService.getUserPostById(userId), HttpStatus.OK);
	}

	
	@GetMapping(value = "/v1/getAllUserPost")
	public ResponseEntity<Object> getAllUsersPost() {
		logger.info("Inside getAllUsersPost of AdminController");
		return new ResponseEntity<Object>(adminService.getAllUserPost(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/v1/getUserPostGReaterThanANumber")
	public ResponseEntity<Object> getUserPostByCount(@RequestParam Long postCount) {
		logger.info("Inside getUserPostGReaterThanANumber of AdminController");
		return new ResponseEntity<Object>(adminService.getUserPostByNumber(postCount), HttpStatus.OK);
	}
	
	@GetMapping(value = "/v1/getCompanyName")
	public ResponseEntity<Object> getCompanyNames() {
		logger.info("Inside getCompanyNames of AdminController");
		return new ResponseEntity<Object>(adminService.getCompanyName(), HttpStatus.OK);
	}
}
