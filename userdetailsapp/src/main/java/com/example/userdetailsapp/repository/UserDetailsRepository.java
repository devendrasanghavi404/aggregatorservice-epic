package com.example.userdetailsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userdetailsapp.modal.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

}
