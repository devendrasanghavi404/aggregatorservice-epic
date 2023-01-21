package com.example.gloquorapostapp.controller;

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

import com.example.gloquorapostapp.dto.GloQuoraPostDto;
import com.example.gloquorapostapp.serviceimp.GloQuoraServiceImpl;

@ExtendWith(MockitoExtension.class)
class GloQuoraControllerTest {

	@Mock
	GloQuoraServiceImpl gloQuoraServiceImpl;

	@InjectMocks
	GloQuoraController gloQuoraController;

	@Test
	@DisplayName("Testing add quora post")
	void testAddGloQuoraStatus() {
		GloQuoraPostDto gloQuoraPostDto = new GloQuoraPostDto();
		gloQuoraPostDto.setBody("Hello this post");
		gloQuoraPostDto.setTitle("hi");
		gloQuoraPostDto.setUserId("14");
		gloQuoraPostDto.setPostId("");
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(gloQuoraServiceImpl.addGloQuoraPost(gloQuoraPostDto)).thenReturn(response);
		ResponseEntity<Object> responseFromController = gloQuoraController.addGloQuoraStatus(gloQuoraPostDto);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing update quora post")
	void testupdateGloQuoraStatus() {
		GloQuoraPostDto gloQuoraPostDto = new GloQuoraPostDto();
		gloQuoraPostDto.setBody("Hello this post");
		gloQuoraPostDto.setTitle("hi");
		gloQuoraPostDto.setUserId("14");
		gloQuoraPostDto.setPostId("1");
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(gloQuoraServiceImpl.updateGloQuoraPost(gloQuoraPostDto)).thenReturn(response);
		ResponseEntity<Object> responseFromController = gloQuoraController.updateGloQuoraStatus(gloQuoraPostDto);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing get all  quora post")
	void getAllGloQuoraStatus() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(gloQuoraServiceImpl.getAllGloQuoraPost()).thenReturn(response);
		ResponseEntity<Object> responseFromController = gloQuoraController.getAllGloQuoraStatus();
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing delete  quora post")
	void deleteGloQuoraStatus() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(gloQuoraServiceImpl.deleteGloQuoraPost("14")).thenReturn(response);
		ResponseEntity<Object> responseFromController = gloQuoraController.deleteGloQuoraStatus("14");
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

}
