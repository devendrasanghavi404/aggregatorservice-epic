package com.example.adminapp.service;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public interface AdminService {

	public Object getUserPostById(String userId);

	public Object getAllUserPost();

	public Object getUserPostByNumber(Long postCount);

	public Object getCompanyName();

}
