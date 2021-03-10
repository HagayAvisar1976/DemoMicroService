package com.example.spring.microservice.demo.controllers;

import com.example.spring.microservice.demo.dao.PostRepository;
import com.example.spring.microservice.demo.dao.UserDaoService;
import com.example.spring.microservice.demo.dao.UserRepository;
import com.example.spring.microservice.demo.model.Post;
import com.example.spring.microservice.demo.model.User;
import com.example.spring.microservice.demo.model.UserNotFoundExeption;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
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

  @Autowired
  private PostRepository postRepository;


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


  @GetMapping("/jpa/users/{id}/posts")
  public List<Post> returnUserPosts(@PathVariable int id){
    Optional<User> user = userRepository.findById(id);
    if(!user.isPresent()){
      throw new UserNotFoundExeption("id-" +id);
    }

    return user.get().getPosts();
  }

  @PostMapping("/jpa/users/{id}/posts")
  public ResponseEntity createPost(@PathVariable int id, @Valid @RequestBody Post post){

    Optional<User> user = userRepository.findById(id);
    if(!user.isPresent()){
      throw new UserNotFoundExeption("id-" +id);
    }

    post.setUser(user.get());
    postRepository.save(post);

    //User saveduser = userRepository.save(user);

    URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
    return ResponseEntity.created(location).build();
  }


}
