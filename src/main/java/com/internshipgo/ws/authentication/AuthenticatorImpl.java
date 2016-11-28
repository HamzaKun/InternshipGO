package com.internshipgo.ws.authentication;


import com.internshipgo.Model.User;
import com.internshipgo.Model.UserType;


/**
 * Created by BinaryTree on 2016/11/28.
 */
public class AuthenticatorImpl implements Authenticator {
    @Override
    public boolean SignIn(User user, UserType type) {
        switch (type){
            case company:
                break;
            case companyAgent:
                break;
            case Student:
                break;
        }
        return false;
    }

    @Override
    public boolean SignUp(User user) {
        return false;
    }

    @Override
    public boolean LogOut(User user) {
        return false;
    }
}
