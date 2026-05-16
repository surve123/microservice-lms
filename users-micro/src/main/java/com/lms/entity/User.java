package com.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*

* @Entity
* This annotation tells Hibernate:
* "Create table in database"
  */
  @Entity
  public class User {

  /*

  * @Id
  * Primary Key
    */
    @Id

  /*

  * Auto increment id
    */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  // User name
  private String name;

  // User email
  private String email;

  // Default constructor
  public User() {

  }

  // Parameterized constructor
  public User(Long id, String name, String email) {
  this.id = id;
  this.name = name;
  this.email = email;
  }

  // Getter Setter

  public Long getId() {
  return id;
  }

  public void setId(Long id) {
  this.id = id;
  }

  public String getName() {
  return name;
  }

  public void setName(String name) {
  this.name = name;
  }

  public String getEmail() {
  return email;
  }

  public void setEmail(String email) {
  this.email = email;
  }
  }
