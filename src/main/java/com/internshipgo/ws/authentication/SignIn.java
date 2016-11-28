package com.internshipgo.ws.authentication;

import com.internshipgo.Model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by BinaryTree on 2016/11/28.
 * Web Service Interface
 */
@WebService
public interface SignIn {
    @WebMethod
    boolean signUp(User user);
}
