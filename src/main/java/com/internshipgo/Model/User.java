package com.internshipgo.Model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hamza on 11/9/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable{
    @Id
    //@GeneratedValue
    private Long id;
    private String email;
    private String password;

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
}
