package com.internshipgo.model;

import javax.persistence.Entity;

/**
 * Created by choaib on 01/01/16.
 */

@Entity
public class YearHead extends User {
    private String name;
    private String description;
    private String address;

    public YearHead() {
        super();
    }
    public YearHead(String mail, String password) {
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
