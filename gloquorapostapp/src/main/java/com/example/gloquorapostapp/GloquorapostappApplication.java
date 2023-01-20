package com.example.gloquorapostapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GloquorapostappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GloquorapostappApplication.class, args);
	}

}
