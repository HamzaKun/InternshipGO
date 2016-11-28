package com.internshipgo.ws.authentication;

import com.internshipgo.Model.User;

import javax.jws.WebService;

/**
 * Created by BinaryTree on 2016/11/28.
 * Web Service Implementer
 */
@WebService(endpointInterface = "com.internshipgo.ws.authentication.SignIn")
public class SignInImpl implements SignIn {
    private String username;
    private String password;
    @Override
    public boolean signUp(User user) {
        username = user.getUsername();
        password = user.getPassword();
        System.out.println("Get Message");
        return searchUserInDB(username,password);
    }
    private boolean searchUserInDB(String username,String password){

        return true;
    }
}
