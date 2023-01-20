package com.example.userdetailsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userdetailsapp.modal.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
