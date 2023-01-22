package com.example.gloquorapostapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.gloquorapostapp.modal.GloQuoraPost;

@Repository
public interface GloQuoraRepository extends MongoRepository<GloQuoraPost, String>{

}
