package com.example.adminapp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="userdetailsapp")
public interface UserDetailsProxy {
	
	@GetMapping(value = "/v1/getAllUsersDetails")
	public ResponseEntity<Object> getAllUserDetails();

}
