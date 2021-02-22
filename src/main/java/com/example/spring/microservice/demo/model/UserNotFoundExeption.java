package com.example.spring.microservice.demo.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExeption extends RuntimeException {

  public UserNotFoundExeption(String message) {
    super(message);
  }
}
