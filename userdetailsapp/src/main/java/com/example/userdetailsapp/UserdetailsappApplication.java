package com.example.userdetailsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserdetailsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserdetailsappApplication.class, args);
	}

}
