package com.internshipgo.view;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Created by hamza on 04/01/17.
 */
public class UpdateEmailForm {
    @Email@NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
