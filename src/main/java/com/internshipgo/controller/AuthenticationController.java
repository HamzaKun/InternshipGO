package com.internshipgo.controller;

import com.internshipgo.model.CompanyAgent;
import com.internshipgo.model.Student;
import com.internshipgo.model.User;
import com.internshipgo.model.YearHead;
import com.internshipgo.model.repository.CompanyAgentDao;
import com.internshipgo.model.repository.StudentDao;
import com.internshipgo.model.repository.UserDao;
import com.internshipgo.model.repository.YearHeadDao;
import com.internshipgo.view.LoginForm;
import com.internshipgo.view.SignUpForm;
import com.internshipgo.view.UpdateEmailForm;
import com.internshipgo.view.UpdateNameForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by hamza on 15/01/17.
 */
@Controller
public class AuthenticationController {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CompanyAgentDao companyAgentDao;
    @Autowired
    private YearHeadDao yearHeadDao;
    @Autowired
    private UserDao userDao;

    @GetMapping("/createUser")
    public String showCreateUserForm() {
        return "my-account";
    }

    @PostMapping("/createUser")
    public String checkUserInfo(LoginForm loginForm, Model model, HttpSession session, @Valid SignUpForm signUpForm, BindingResult bindingResult) {
        System.out.println("in createUser Post method");
        System.out.println(signUpForm);
        //To check if the email is already in use
        List<User> users = userDao.findByEmail(signUpForm.getEmail());
        if(bindingResult.hasErrors() || !signUpForm.getConfPassword().equals((String) signUpForm.getPassword())
                || users.size() != 0) {
            return "redirect:my-account#tab2";
        }else{
            System.out.println(signUpForm);
            if(signUpForm.getUserType().equals("Student")) {
                Student student = new Student();
                student.setEmail(signUpForm.getEmail());
                student.setPassword(signUpForm.getPassword());
                student.setUserName(signUpForm.getUserName());
                student.setSpecialization(signUpForm.getSpecialization());
                student.setName(signUpForm.getUserName());
                session.setAttribute("activeUser",student);
                studentDao.save((Student)student);
                return "index-2";
            } else if(signUpForm.getUserType().equals("Company")){
                CompanyAgent company = new CompanyAgent();
                company.setUserName(signUpForm.getUserName());
                company.setEmail(signUpForm.getEmail());
                company.setPassword(signUpForm.getPassword());
                /**
                 * We'll use this field to know the company of the user;
                 * It's mapped with the companyName in the company(id)
                 * and the InternshipOffer(companyName)
                 */
                company.setCompanyname(signUpForm.getOrganame());
                session.setAttribute("activeUser", company);

                companyAgentDao.save((CompanyAgent) company);
                return "index-3";
            } else if(signUpForm.getUserType().equals("YearHead")){
                YearHead yearHead = new YearHead();
                yearHead.setUserName(signUpForm.getUserName());
                yearHead.setEmail(signUpForm.getEmail());
                yearHead.setPassword(signUpForm.getPassword());
                yearHead.setDepartement(signUpForm.getDepartement());
                session.setAttribute("activeUser", yearHead);
                yearHeadDao.save((YearHead)yearHead);
                return "index-4";
            }
            return "redirect:/index";
        }

    }

    @PostMapping("/updateUserName")
    public String updateUser(@Valid UpdateNameForm nameForm, HttpSession session, BindingResult bindingResult, Model model) {
        model.addAttribute("nameForm", new UpdateNameForm());
        model.addAttribute("mailForm", new UpdateEmailForm());
        User user = (User) session.getAttribute("activeUser");
        if (user != null  && (user.getClass() == Student.class) ) {
            if ( !bindingResult.hasErrors() ) {
                userDao.updateUserName(user.getId(), nameForm.getUserName());
                return "add-resume";
            }
        } else {
            return "my-account";
        }
        return "redirect:/add-resume";
    }


}
