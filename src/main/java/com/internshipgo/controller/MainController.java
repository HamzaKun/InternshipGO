package com.internshipgo.controller;

import com.internshipgo.Model.User;
import com.internshipgo.Model.repository.UserDao;
import com.internshipgo.view.LoginForm;
import com.internshipgo.view.SignUpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String Try() {
        return "fileUpload";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/createUser")
    public String showCreateUserForm(Model model, SignUpForm signUpForm) {
        List<String> userTypes= new ArrayList<String>();
        userTypes.add("Student");
        userTypes.add("Company");
        model.addAttribute("userTypes", userTypes);
        return "SignupPage";
    }

    @PostMapping("/createUser")
    public String checkUserInfo(Model model, HttpSession session, @Valid SignUpForm signUpForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || !signUpForm.getConfPassword().equals((String) signUpForm.getPassword())) {
            return "SignupPage";
        }else{
            System.out.println(signUpForm);
            User user = new User();
            user.setEmail(signUpForm.getEmail());
            user.setPassword(signUpForm.getPassword());
            user.setField(signUpForm.getField());
            userDao.save(user);
            session.setAttribute("activeUsesr", user);
            return "redirect:/results";
        }

    }

    @GetMapping("/login")
    public String loginRedirect(LoginForm loginForm) {
        return "LoginPage";
    }

    @PostMapping("/login")
    public String loginAction(@Valid LoginForm loginForm, BindingResult bindingResult) {
        User user = userDao.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if( user == null ) {
            return "LoginPage";
        }
        System.out.println(user);
        return  "redirect:/results";
    }

}
