package com.example.gloquorapostapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gloquorapostapp.dto.GloQuoraPostDto;

@Service
public interface GloQuoraService {

	public Object addGloQuoraPost(GloQuoraPostDto gloQuoraPostDto);

	public Object updateGloQuoraPost(GloQuoraPostDto gloQuoraPostDto);

	public Object getAllGloQuoraPost();

	public Object deleteGloQuoraPost(String postId);


	
}
