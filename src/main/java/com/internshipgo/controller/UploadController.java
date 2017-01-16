package com.internshipgo.controller;

/**
 * Created by user on 11/01/2017.
 */


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadController {
    @Autowired
    private Environment env;
    private static final Logger logger = LoggerFactory
            .getLogger(FileUploadController.class);

    @RequestMapping("/Nasa")
    public String uploadRedirect1() {
        return "apply-offer";
    }

    @RequestMapping(value = "/uploadFileNasa", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@RequestParam("name") String name,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("email") String email,
                             @RequestParam("location") String location,
                             @RequestParam("photo") MultipartFile photo,
                             @RequestParam("motiv") String motiv
                             ) {
        String uploadPath = env.getProperty("paths.uploadedFiles");
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File(uploadPath);
                if (!dir.exists())
                    dir.mkdirs();
                // recuperation des data
                /*System.out.println("email : " + email);
                System.out.println("name : " + name);
                System.out.println("location : " + location);
                System.out.println("Motiv : " + motiv);*/

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                return "You successfully uploaded file=" + name;
                //return "fileUpload";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
                //return "fileUpload";
            }
        } else {
            return "You failed to upload " + name
                    + " because the file was empty.";
        }
    }

}

