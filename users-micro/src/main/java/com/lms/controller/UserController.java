package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lms.entity.User;
import com.lms.service.UserService;

/*

* @RestController
* API layer
  */
  @RestController

/*

* Base URL
  */
  @RequestMapping("/users")
  public class UserController {

  @Autowired
  private UserService userService;

  /*

  * CREATE USER
    */
    @PostMapping
    public User saveUser(@RequestBody User user) {

    return userService.saveUser(user);
    }

  /*
  * GET ALL USERS
    */
    @GetMapping
    public List<User> getAllUsers() {

    return userService.getAllUsers();
    }

  /*

  * GET USER BY ID
    */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {

    return userService.getUserById(id);
    }

  /*

  * UPDATE USER
    */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
    @RequestBody User user) {

    return userService.updateUser(id, user);
    }

  /*

  * DELETE USER
    */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

    userService.deleteUser(id);

    return "User Deleted Successfully";
    }
    }
