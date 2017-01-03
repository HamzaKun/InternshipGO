package com.internshipgo.view;

import javax.validation.constraints.NotNull;

/**
 * Created by hamza on 03/01/17.
 */
public class UpdateNameForm {
    @NotNull
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
