package com.example.spring.microservice.demo.controllers;

import com.example.spring.microservice.demo.dao.UserDaoService;
import com.example.spring.microservice.demo.model.User;
import com.example.spring.microservice.demo.model.UserNotFoundExeption;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UsersController {

  @Autowired
  private UserDaoService userDaoService;

  @GetMapping("/users")
  public List<User> returnAllUsers(){
    return userDaoService.findAll();
  }

  @GetMapping("/users/{id}")
  public User getUser(@PathVariable int id) {
    User user =  userDaoService.findOne(id);

    if(user ==null){
      throw new UserNotFoundExeption("user - "+ id);
    }


    return user;
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable int id) {
    User user =  userDaoService.deleteById(id);
    if(user ==null){
      throw new UserNotFoundExeption("user - "+ id);
    }

  }

  @PostMapping("/users")
  public ResponseEntity createUser(@Valid @RequestBody User user){

    User saveduser = userDaoService.save(user);

    URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
    return ResponseEntity.created(location).build();


  }
}
