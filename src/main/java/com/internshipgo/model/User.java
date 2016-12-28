package com.internshipgo.model;

import javax.persistence.*;

/**
 * Created by Hamza on 11/9/2016.
 */
@Entity
@Inheritance    (strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String field;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {

    }

    public User(String mail, String password) {
        email = mail;
        this.password = password;
    }

    public String toString() {
        return "(" + email + ", " + password + ", " + field + ")";
    }
}
