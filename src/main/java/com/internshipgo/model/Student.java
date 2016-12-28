package com.internshipgo.model;

/**
 * Created by Hamza on 11/9/2016.
 */
public class Student extends User{
    private String name;
    private String description;
    private String address;

    public Student() {
        super();
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
