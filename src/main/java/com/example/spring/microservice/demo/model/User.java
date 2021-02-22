package com.example.spring.microservice.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@ApiModel(description = "This is the user model")
public class User {

  private Integer id;

  @Size(min = 2, message = "Name should have at least 2 charecters")
  @ApiModelProperty(notes = "name should be at least to charecters")
  private String name;

  @Past
  @ApiModelProperty(notes = "this date can only be in the past")
  private Date birthDate;

  public User(){}

  public User(Integer id, String name, Date birthDate) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", birthDate=" + birthDate + '}';
  }
}