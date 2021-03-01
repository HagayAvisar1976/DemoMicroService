package com.example.spring.microservice.demo.controllers;

import com.example.spring.microservice.demo.model.Name;
import com.example.spring.microservice.demo.model.PersonV1;
import com.example.spring.microservice.demo.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

  /*
  This controller represent different ways for managing API versioning
   */

  // Method 1: different url mapping
  @GetMapping("v1/person")
  public PersonV1 personv1(){
    return new PersonV1("Bob Charlie");
  }

  @GetMapping("v2/person")
  public PersonV2 personV2(){
    return  new PersonV2(new Name("Bob","Chrlie"));
  }


  // Method 2: version paramter
  @GetMapping(value="/person/param",params = "version=1")
  public PersonV1 param1(){
    return new PersonV1("Bob Charlie");
  }

  @GetMapping(value="/person/param",params = "version=2")
  public PersonV2 param2(){
    return  new PersonV2(new Name("Bob","Chrlie"));
  }


  // Method 3: header version
  @GetMapping(value="/person/header",headers = "X-API-VERSION=1")
  public PersonV1 header1(){
    return new PersonV1("Bob Charlie");
  }

  @GetMapping(value="/person/header",headers = "X-API-VERSION=2")
  public PersonV2 header2(){
    return  new PersonV2(new Name("Bob","Chrlie"));
  }

  // Method 4: producers version
  @GetMapping(value="/person/produces",produces = "application/vnd.company.app-v1+json")
  public PersonV1 produces1(){
    return new PersonV1("Bob Charlie");
  }

  @GetMapping(value="/person/produces",produces = "application/vnd.company.app-v2+json")
  public PersonV2 produces2(){
    return  new PersonV2(new Name("Bob","Chrlie"));
  }

}
