package com.example.gloquorapostapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gloquorapostapp.modal.SecureUser;

@Repository
public interface SecurityUserRepo extends JpaRepository<SecureUser, Long> {

	SecureUser findByUserName(String userName);

}
