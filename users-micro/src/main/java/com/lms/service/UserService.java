package com.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.entity.User;
import com.lms.repository.UserRepository;

/*

* @Service
* Business logic layer
  */
  @Service
  public class UserService {

  @Autowired
  private UserRepository userRepository;

  /*

  * Save User
    */
    public User saveUser(User user) {

    return userRepository.save(user);
    }

  /*

  * Get all users
    */
    public List<User> getAllUsers() {

    return userRepository.findAll();
    }

  /*

  * Get user by id
    */
    public User getUserById(Long id) {

    return userRepository.findById(id).orElse(null);
    }

  /*

  * Delete user
    */
    public void deleteUser(Long id) {

    userRepository.deleteById(id);
    }

  /*

  * Update user
    */
    public User updateUser(Long id, User user) {

    User oldUser = userRepository.findById(id).orElse(null);

    if(oldUser != null) {

     oldUser.setName(user.getName());
     oldUser.setEmail(user.getEmail());

     return userRepository.save(oldUser);
   

    }

    return null;
    }
    }
