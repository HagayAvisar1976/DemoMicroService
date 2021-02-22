package com.example.spring.microservice.demo.controllers;

import com.example.spring.microservice.demo.model.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

  @GetMapping("/filtering")
  public SomeBean getSomeBean(){
    return new SomeBean("field1","field2","field2");
  }

  @GetMapping("/filtering-dynamic")
  public MappingJacksonValue dynamicFilterGetSomeBean(){
    SomeBean someBean =  new SomeBean("field1","field2","field2");

    SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);

    MappingJacksonValue mapping = new MappingJacksonValue(someBean);
    mapping.setFilters(filters);

    return mapping;

  }
}
