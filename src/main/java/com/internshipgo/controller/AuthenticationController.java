package com.internshipgo.controller;

import com.internshipgo.model.*;
import com.internshipgo.model.repository.*;
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
import java.util.ArrayList;
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
    @Autowired
    private CompanyDao companyDao;

    @GetMapping("/createUser")
    public String showCreateUserForm() {
        return "my-account";
    }

    @PostMapping("/createUser")
    public String checkUserInfo(LoginForm loginForm, Model model, HttpSession session, @Valid SignUpForm signUpForm, BindingResult bindingResult) {
        System.out.println("in createUser Post method");
        System.out.println(signUpForm);
        //To check if the email is already in use
        //TODO Add an error
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
                studentDao.save(student);
                return "index-2";
            } else if(signUpForm.getUserType().equals("Company")){
                CompanyAgent companyAgent = new CompanyAgent();
                companyAgent.setUserName(signUpForm.getUserName());
                companyAgent.setEmail(signUpForm.getEmail());
                companyAgent.setPassword(signUpForm.getPassword());
                /**
                 * We'll use this field to know the companyAgent of the user;
                 * It's mapped with the companyName in the companyAgent(id)
                 * and the InternshipOffer(companyName)
                 */
                companyAgent.setCompanyname(signUpForm.getOrganame());
                Company company1 = companyDao.getCompanyById(signUpForm.getOrganame());
                if (company1 != null) {
                    List<CompanyAgent> agents = company1.getAgents();
                    agents.add(companyAgent);
                    company1.setAgents(agents);
                    companyAgent.setCompany(company1);
                    companyDao.save(company1);
                }else {
                    Company company = new Company();
                    company.setId(signUpForm.getOrganame());
                    List<CompanyAgent> agents = new ArrayList<>();
                    companyAgent.setCompany(company);
                    agents.add(companyAgent);
                    company.setAgents(agents);
                    companyDao.save(company);
                }
                session.setAttribute("activeUser", companyAgent);
                return "index-3";
            } else if(signUpForm.getUserType().equals("YearHead")){
                YearHead yearHead = new YearHead();
                yearHead.setUserName(signUpForm.getUserName());
                yearHead.setEmail(signUpForm.getEmail());
                yearHead.setPassword(signUpForm.getPassword());
                yearHead.setDepartement(signUpForm.getDepartement());
                session.setAttribute("activeUser", yearHead);
                yearHeadDao.save(yearHead);
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
