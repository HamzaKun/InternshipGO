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
    private String userName;
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
    private String specialization;
    private String departement;
    private String organame;

    public String toString() {
        return ("userType :" + userType + ", userName: " + ", email: " +email + "pass: " + password +", conf: " + confPassword );
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
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



    public String getUserType() {
        return userType;
    }


    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getOrganame() {
        return organame;
    }

    public void setOrganame(String organame) {
        this.organame = organame;
    }
}
