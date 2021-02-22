package com.example.spring.microservice.demo.dao;

import com.example.spring.microservice.demo.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

  private static List<User> users = new ArrayList<User>();

  private static int usersCount = 3;

  static {
    users.add(new User(1,"Adam", new Date()));
    users.add(new User(2,"Eva", new Date()));
    users.add(new User(3,"jack", new Date()));
  }

  public List<User> findAll(){
    return this.users;
  }

  public User save(User user){
    if(user.getId() == null){
      user.setId(++usersCount);
    }
    this.users.add(user);
    return user;
  }

  public User findOne(int id){
    for(User user: users){
      if(user.getId() == id){
        return user;
      }
    }
    return null;
  }

  public User deleteById(int id) {
    Iterator<User> iterator = users.iterator();
    while (iterator.hasNext()) {
      User user = iterator.next();
      if (user.getId() == id) {
        iterator.remove();
        return user;
      }
    }
    return null;
  }


}
