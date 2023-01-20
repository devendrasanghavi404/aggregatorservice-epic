package com.example.userdetailsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userdetailsapp.modal.Geo;


@Repository
public interface GeoRepository extends JpaRepository<Geo, Long>{

}
