package com.example.gloquorapostapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.gloquorapostapp.modal.GloQuoraPost;

public interface GloQuoraRepository extends MongoRepository<GloQuoraPost, String>{

}
