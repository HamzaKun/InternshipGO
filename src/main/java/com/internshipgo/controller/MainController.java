package com.internshipgo.controller;

import com.internshipgo.Model.User;
import com.internshipgo.Model.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@Component
public class MainController {

    /*@Autowired
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String Try(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @Autowired
    JdbcTemplate jdbcTemplate;



    @RequestMapping("/create")
    public String Create(String mail, String password) {
        long userId = 0;
        try {
            User user = new User(mail, password);
            //userDao.save(user);
            //List<Object[]> param = Arrays.asList(mail, pas);//{new String[]{mail, password}});
            jdbcTemplate.batchUpdate("INSERT INTO USER(email, password) VALUES(test, test)");
            userId = user.getId();
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }/*@Bean/**/

}
