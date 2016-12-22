package com.internshipgo.view;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hamza on 10/12/16.
 */
public class SignUpForm {
    @NotNull
    private String userType;
    @NotNull
    @Size(min=6, max=30)
    @Email
    private String email;
    @NotNull
    @Size(min=8, max=30)
    private String password;
    @NotNull
    @Size(min=8, max=30)
    //@FieldMatch(first = "password", second = "confirmPassword", message = "Passowords are not equal.")
    private String confPassword;
    @NotNull
    private String field;

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String toString() {
        return "Email: " + email + ", Password: " + password + ", Field: " + field;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
