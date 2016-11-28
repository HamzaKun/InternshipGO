package com.internshipgo.ws.authentication;

import com.internshipgo.Model.User;
import com.internshipgo.Model.UserType;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by BinaryTree on 2016/11/28.
 */
@WebService
public interface Authenticator {
    @WebMethod boolean SignIn(User user, UserType type);
    @WebMethod boolean SignUp(User user);
    @WebMethod boolean LogOut(User user);
}
