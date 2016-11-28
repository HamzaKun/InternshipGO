package com.internshipgo.Model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by Hamza on 11/9/2016.
 */
@Entity
public class Student extends User{
    private String name;
    private String description;
    private String address;

    public Student() {
    }

    public Student(String mail, String password) {
        super(mail, password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
