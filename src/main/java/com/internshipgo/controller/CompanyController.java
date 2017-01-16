package com.internshipgo.controller;

import com.internshipgo.model.CompanyAgent;
import com.internshipgo.model.Student;
import com.internshipgo.model.User;
import com.internshipgo.model.repository.InternshipOfferDao;
import com.internshipgo.model.InternshipOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamza on 15/01/17.
 */
@Controller
public class CompanyController {
    @Autowired
    InternshipOfferDao internshipOfferDao;

    @RequestMapping("/manage-applications")
    public String manageApplications(HttpSession session, Model model) {
        User user = (User) session.getAttribute("activeUser");

        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            CompanyAgent companyAgent = (CompanyAgent) user;
            List<Student> applicants = new ArrayList<Student>();
            List<InternshipOffer> internshipOffers = internshipOfferDao.getCompanyOffer(
                    companyAgent.getCompanyname());
            for(int i =0; i < internshipOffers.size(); i++) {
                applicants.addAll(internshipOffers.get(i).getStudents());
            }
            model.addAttribute("lists", applicants);
            session.setAttribute("activeUser", user);
            return "manage-applications";
        } else {
            return "index";
        }
        //return "manage-applications";
    }

}
