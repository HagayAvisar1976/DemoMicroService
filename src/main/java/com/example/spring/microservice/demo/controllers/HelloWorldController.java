package com.example.spring.microservice.demo.controllers;

import com.example.spring.microservice.demo.model.HelloWorldBean;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @Autowired
  MessageSource messageSource;

  @GetMapping("/hello")
  public String HelloWorld( ){
    return "hello world";
  }

  @GetMapping("/hello-bean")
  public HelloWorldBean HelloWorldBean( ){
    return new HelloWorldBean("Hello world");
  }

  @GetMapping("/hello-bean/param/{name}")
  public HelloWorldBean HelloWorldBeanParam(@PathVariable String name ){
    return new HelloWorldBean("Hello world" + name );
  }


  @GetMapping("/hello-inter")
  public String HelloWorldInter(/*@RequestHeader(name = "Accept-Language",required = false) Locale locale*/){

    return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    //return messageSource.getMessage("good.morning.message",null,locale);
    //return "hello world";
  }

}
