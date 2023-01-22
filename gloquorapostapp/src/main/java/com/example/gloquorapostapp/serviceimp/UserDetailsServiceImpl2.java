package com.example.gloquorapostapp.serviceimp;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gloquorapostapp.modal.SecureUser;
import com.example.gloquorapostapp.repository.SecurityUserRepo;
import com.example.gloquorapostapp.security.MyUserDetails;



@Service
public class UserDetailsServiceImpl2 implements UserDetailsService {

	Logger log = LoggerFactory.getLogger(UserDetailsService.class);
			
	@Autowired
	SecurityUserRepo securityUserRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		log.info("Inside loadUserByUsername of UserDetailsServiceImpl");
		SecureUser secureUser = securityUserRepo.findByUserName(userName);
		log.info("Data from security user is :{}",secureUser);
		return new  MyUserDetails(secureUser);
	}
}


