package com.example.spring.microservice.demo.controllers;

import com.example.spring.microservice.demo.dao.UserDaoService;
import com.example.spring.microservice.demo.dao.UserRepository;
import com.example.spring.microservice.demo.model.User;
import com.example.spring.microservice.demo.model.UserNotFoundExeption;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAController {

  @Autowired
  private UserRepository userRepository;


  @GetMapping("/jpa/users")
  public List<User> returnAllUsers(){
    return userRepository.findAll();
  }

  @GetMapping("/jpa/users/{id}")
  public User getUser(@PathVariable int id) {
    Optional<User> user =  userRepository.findById(id);

    if(!user.isPresent()){
      throw new UserNotFoundExeption("user - "+ id);
    }


    return user.get();
  }

  @DeleteMapping("/jpa/users/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepository.deleteById(id);
  }

  @PostMapping("/jpa/users")
  public ResponseEntity createUser(@Valid @RequestBody User user){

    User saveduser = userRepository.save(user);

    URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
    return ResponseEntity.created(location).build();


  }
}
