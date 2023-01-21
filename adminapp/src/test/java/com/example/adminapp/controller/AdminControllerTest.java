package com.example.adminapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.adminapp.serviceimpl.AdminserviceImpl;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

	@Mock
	AdminserviceImpl adminserviceImpl;

	@InjectMocks
	AdminController adminController;

	@Test
	@DisplayName("Testing get user post by id")
	void testAddGloQuoraStatus() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(adminserviceImpl.getUserPostById("1")).thenReturn(response);
		ResponseEntity<Object> responseFromController = adminController.getUserPostById("1");
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing get all users post")
	void testgetAllUserPost() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(adminserviceImpl.getAllUserPost()).thenReturn(response);
		ResponseEntity<Object> responseFromController = adminController.getAllUsersPost();
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing get users post by count")
	void testgetUserPostByCount() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(adminserviceImpl.getUserPostByNumber(2l)).thenReturn(response);
		ResponseEntity<Object> responseFromController = adminController.getUserPostByCount(2l);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing get company names")
	void getCompanyNames() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(adminserviceImpl.getCompanyName()).thenReturn(response);
		ResponseEntity<Object> responseFromController = adminController.getCompanyNames();
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

}
