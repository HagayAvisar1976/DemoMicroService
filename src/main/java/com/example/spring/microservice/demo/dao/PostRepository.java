package com.example.spring.microservice.demo.dao;

import com.example.spring.microservice.demo.model.Post;
import com.example.spring.microservice.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

}
