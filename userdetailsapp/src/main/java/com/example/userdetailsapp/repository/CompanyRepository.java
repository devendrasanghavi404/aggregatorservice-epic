package com.example.userdetailsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userdetailsapp.modal.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
