package com.example.gloquorapostapp.controller;

import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gloquorapostapp.dto.GloQuoraPostDto;
import com.example.gloquorapostapp.exception.GloQuoraException;
import com.example.gloquorapostapp.service.GloQuoraService;

@RestController
public class GloQuoraController {

	Logger logger = LoggerFactory.getLogger(GloQuoraController.class);

	@Autowired
	GloQuoraService gloQuoraService;

	@PostMapping(value = "/v1/addQuoraPost")
	public ResponseEntity<Object> addGloQuoraStatus(@RequestBody GloQuoraPostDto gloQuoraPostDto) {
		vaildator(gloQuoraPostDto);
		return new ResponseEntity<Object>(gloQuoraService.addGloQuoraPost(gloQuoraPostDto), HttpStatus.OK);
	}

	@PutMapping(value = "/v1/updateQuoraPost")
	public ResponseEntity<Object> updateGloQuoraStatus(@RequestBody GloQuoraPostDto gloQuoraPostDto) {
		return new ResponseEntity<Object>(gloQuoraService.updateGloQuoraPost(gloQuoraPostDto), HttpStatus.OK);
	}

	@GetMapping(value = "/v1/getAllQuoraPost")
	public ResponseEntity<Object> getAllGloQuoraStatus() {
		return new ResponseEntity<Object>(gloQuoraService.getAllGloQuoraPost(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/v1/getAllQuoraPost")
	public ResponseEntity<Object> deleteGloQuoraStatus(@RequestParam String postId) {
		return new ResponseEntity<Object>(gloQuoraService.deleteGloQuoraPost(postId), HttpStatus.OK);
	}

	public void vaildator(GloQuoraPostDto gloQuoraPostDto) {
		if (gloQuoraPostDto.getBody() == null || gloQuoraPostDto.getPostId() == null
				|| gloQuoraPostDto.getTitle() == null || gloQuoraPostDto.getUserId() == null) {
			throw new GloQuoraException("Invalid Request Data");
		}
	}
}
