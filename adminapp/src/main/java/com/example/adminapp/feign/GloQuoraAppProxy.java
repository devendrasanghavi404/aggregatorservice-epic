package com.example.adminapp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="gloquorapplication")
public interface GloQuoraAppProxy {
	
	@GetMapping(value = "/v1/getAllQuoraPost")
	public ResponseEntity<Object> getAllGloQuoraStatus();
}
