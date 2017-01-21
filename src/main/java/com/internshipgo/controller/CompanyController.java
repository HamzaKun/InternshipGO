package com.internshipgo.controller;

import com.internshipgo.model.*;
import com.internshipgo.model.repository.InternshipOfferDao;
import com.internshipgo.model.repository.OfferStatusDao;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamza on 15/01/17.
 */
@Controller
public class CompanyController {
    @Autowired
    InternshipOfferDao internshipOfferDao;
    @Autowired
    OfferStatusDao offerStatusDao;
    @Autowired
    private Environment env;

    @RequestMapping("/manage-applications")
    public String manageApplications(HttpSession session, Model model) {
        User user = (User) session.getAttribute("activeUser");

        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            CompanyAgent companyAgent = (CompanyAgent) user;
            List<OfferStatus> applicants = offerStatusDao.getApplyingStudents(companyAgent.getCompany().getId());
            model.addAttribute("lists", applicants);
            session.setAttribute("activeUser", user);
            return "/manage-applications";
        } else {
            return "index";
        }
        //return "manage-applications";
    }

    @RequestMapping(value="/accept/{offer_id}", method = RequestMethod.GET)
    public String acceptApplicant(HttpSession session, HttpServletResponse response, @PathVariable("offer_id") Long offerId) {
        User user = (User) session.getAttribute("activeUser");
        CompanyAgent companyAgent = (CompanyAgent) user;
        if (user == null) {
            return "redirect:/my-account";
        }else if( user.getClass() == CompanyAgent.class ) {
            OfferStatus offerStatus = offerStatusDao.getOfferStatusById(offerId);
            /**
             * To check if he's trying to change the status of his company's offers
             */
            if ( offerStatus.getInternshipOffer().getCompanyName() == companyAgent.getCompanyname()){
                offerStatus.setCompanyResponse(OfferResponses.ACCEPTED);
                offerStatusDao.save(offerStatus);
                return "redirect:/manage-applications";
            } else {
                return "redirect:/manage-applications";
            }
        } else {
            return "index";
        }
    }

    @RequestMapping(value="/refuse/{offer_id}", method = RequestMethod.GET)
    public String refuseApplicant(HttpSession session, HttpServletResponse response, @PathVariable("offer_id") Long offerId) {
        User user = (User) session.getAttribute("activeUser");
        CompanyAgent companyAgent = (CompanyAgent) user;
        if (user == null) {
            return "redirect:/my-account";
        }else if( user.getClass() == CompanyAgent.class ) {
            OfferStatus offerStatus = offerStatusDao.getOfferStatusById(offerId);
            /**
             * To check if he's trying to change the status of his company's offers
             */
            if ( offerStatus.getInternshipOffer().getCompany().getId() == companyAgent.getCompany().getId()){
                offerStatus.setCompanyResponse(OfferResponses.REFUSED);
                offerStatusDao.save(offerStatus);
                return "redirect:/manage-applications";
            } else {
                return "redirect:/manage-applications";
            }
        } else {
            return "index";
        }
    }

    @RequestMapping("/testtest")
    public void test(HttpSession session) {
        List<OfferStatus> students = offerStatusDao.getApplyingStudents("Google");
    }
}
