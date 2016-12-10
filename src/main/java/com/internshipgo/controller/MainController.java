package com.internshipgo.controller;

import com.internshipgo.Model.User;
import com.internshipgo.Model.repository.UserDao;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
//@Component
public class MainController extends WebMvcConfigurerAdapter {

    /*@Autowired
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String Try(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    /*@Autowired
    JdbcTemplate jdbcTemplate;
*/
    @RequestMapping("/signup")
    public String SignUpRedirection(String mail, String password) {

        return "SignupPage";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/createUser")
    public String showCreateUserForm(Model model, SignUpForm signUpForm) {
        //model.addAttribute("signUpForm", signUpForm);
        return "index";
    }


    @PostMapping("/createUser")
    public String checkUserInfo(Model model, HttpSession session, @Valid SignUpForm signUpForm, BindingResult bindingResult) {
        //model.addAttribute("signUpForm", signUpForm);
        if(bindingResult.hasErrors() || !signUpForm.getConfPassword().equals((String) signUpForm.getPassword())) {

            return "index";
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

}
