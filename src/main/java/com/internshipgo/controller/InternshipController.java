package com.internshipgo.controller;

import com.internshipgo.model.InternshipOffer;
import com.internshipgo.model.Student;
import com.internshipgo.model.User;
import com.internshipgo.model.repository.InternshipOfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by awang on 2017/1/5.
 */
@Controller
public class InternshipController {
    @Autowired
    InternshipOfferDao internshipOfferDao;

    // TODO: 2017/1/5 add known student into selected offer
    @RequestMapping("/apply")
    private String applyInternship(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");

        List<Student> students = new ArrayList<Student>();
        students.add(new Student());
        List<InternshipOffer> list = new ArrayList<InternshipOffer>();
        InternshipOffer test = new InternshipOffer();
        test.setStudents(students);
        list.add(test);
        internshipOfferDao.save(list);
        return "index";
    }


}
