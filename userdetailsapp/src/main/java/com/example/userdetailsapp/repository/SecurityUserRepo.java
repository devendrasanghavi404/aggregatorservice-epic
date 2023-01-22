package com.example.userdetailsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userdetailsapp.modal.SecureUser;

@Repository
public interface SecurityUserRepo extends JpaRepository<SecureUser, Long> {

	public SecureUser findByUserName(String userName);

}
