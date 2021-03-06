package com.internshipgo.controller;

import com.internshipgo.model.*;
import com.internshipgo.model.repository.OfferStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by hamza on 20/01/17.
 */
@Controller
public class ApplicationsController {
    @Autowired
    OfferStatusDao offerStatusDao;

    @RequestMapping("/manage-application")
    public String manageResumes(HttpSession session, Model model) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:my-account";
        }else if( user.getClass() == CompanyAgent.class) {
            session.setAttribute("activeUser", user);
            return "index-3";
        } else if( user.getClass() == Student.class) {
            ArrayList<OfferStatus> appliedOffers = (ArrayList<OfferStatus>) offerStatusDao.getOfferStatusByStudent((Student) user);
            session.setAttribute("activeUser" , user);
            model.addAttribute("offers", appliedOffers);
            return "manage-application";
        } else if ( user.getClass() == YearHead.class) {
            session.setAttribute("activeUser", user);
            return "index-4";
        }
        return "redirect:/index";
    }

/*    @RequestMapping("/manage-application2")
    public ModelAndView manageResumes2(HttpSession session, Model model) {
        ModelAndView dg = new ModelAndView("manage-application.html");
        return dg;
    }*/

    @RequestMapping("/studentAccept/{offerId}")
    public String studentAccept(HttpSession session, @PathVariable("offerId") Long offerId) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:/index";
        } else if ( user.getClass() == Student.class ) {
            OfferStatus offerStatus = offerStatusDao.getOfferStatusById(offerId);
            offerStatus.setStudentResponse(OfferResponses.ACCEPTED);
            offerStatusDao.save(offerStatus);
            return "redirect:/manage-application";
        }
        return "redirect:/index";
    }
    @RequestMapping("/studentRefuse/{offerId}")
    public String studentRefuse(HttpSession session, @PathVariable("offerId") Long offerId) {
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            return "redirect:/index";
        } else if ( user.getClass() == Student.class ) {
            OfferStatus offerStatus = offerStatusDao.getOfferStatusById(offerId);
            offerStatus.setStudentResponse(OfferResponses.REFUSED);
            offerStatusDao.save(offerStatus);
            return "redirect:/manage-application";
        }
        return "redirect:/index";
    }
}
