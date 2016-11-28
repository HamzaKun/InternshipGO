package com.internshipgo.test;

import com.internshipgo.ws.authentication.SignInImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by BinaryTree on 2016/11/28.
 * Test Exporter for WebService
 * Deploy the web service to server
 */
public class Exporter {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/hello",new SignInImpl());
    }
}
