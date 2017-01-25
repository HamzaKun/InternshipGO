package com.internshipgo.controller;

/**
 * Created by user on 11/01/2017.
 */


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.internshipgo.model.InternshipOffer;
import com.internshipgo.model.OfferStatus;
import com.internshipgo.model.Student;
import com.internshipgo.model.User;
import com.internshipgo.model.repository.InternshipOfferDao;
import com.internshipgo.model.repository.OfferStatusDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class UploadController {
    @Autowired
    private Environment env;
    @Autowired
    private OfferStatusDao offerStatusDao;
    @Autowired
    private InternshipOfferDao internshipOfferDao;

    private static final Logger logger = LoggerFactory
            .getLogger(FileUploadController.class);

    @RequestMapping("/Nasa/{internshipId}")
    public ModelAndView uploadRedirect1(HttpSession session,@PathVariable("internshipId") Long internshipId) {
        User user = (User) session.getAttribute("activeUser");
        ModelAndView model;
        if (user == null) {
            model = new ModelAndView("redirect:my-account");
            return model;
        }else if( user.getClass() == Student.class) {
            model = new ModelAndView("apply-offer");
            session.setAttribute("activeUser", user);
            model.addObject("iden",internshipId);
            return model;
        } else {
            model = new ModelAndView("index");
            return model;
        }
    }

    @RequestMapping(value = "/uploadFileNasa/{internshipId}", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file,
                             @RequestParam("photo") MultipartFile photo,
                             @RequestParam("motiv") String motiv
                             , HttpSession session, @PathVariable("internshipId") Long internshipId) {
        User user = (User) session.getAttribute("activeUser");
        ModelAndView model;
        String uploadPath = env.getProperty("paths.uploadedFiles");
        if (!file.isEmpty()) {
            try {
                Student student = (Student) user;
                if (offerStatusDao.getOfferStatusByIdAndInternshipId(student.getId(), internshipId) == null) {
                    byte[] bytes = file.getBytes();
                    // Creating the directory to store file
                    File dir = new File(uploadPath);
                    if (!dir.exists())
                        dir.mkdirs();
                    // Create the file on server
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + user.getUserName() + "_" + internshipId);
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();
                    OfferStatus offerStatus = new OfferStatus();
                    offerStatus.setMotivation(motiv);
                    offerStatus.setResumePath(user.getUserName() + "_" + internshipId);
                    offerStatus.setStudent(student);
                    offerStatus.setInternshipOffer(internshipOfferDao.getInternshipOfferById(internshipId));
                    offerStatusDao.save(offerStatus);

                    logger.info("Server File Location="
                            + serverFile.getAbsolutePath());
                }
                model = new ModelAndView("redirect:/browse-jobs");
                //return "You successfully uploaded file=" + user.getUserName()+internshipId;
                return model;
            } catch (Exception e) {
                e.printStackTrace();
                model = new ModelAndView("redirect:/index");
//                return "You failed to upload " + user.getUserName() + " => " + e.getMessage();
                //return "fileUpload";
            }
        } else {
            model = new ModelAndView("redirect:/index");
            return model;
//            return "You failed to upload " + user.getUserName() + " because the file was empty.";
        }
        model = new ModelAndView("redirect:/index");
        return model;
    }

}

