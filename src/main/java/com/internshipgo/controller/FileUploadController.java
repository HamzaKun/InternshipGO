package com.internshipgo.controller;

import com.internshipgo.model.Student;
import com.internshipgo.model.User;
import com.internshipgo.model.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

/**
 * Created by hamza on 21/12/16.
 */
@Controller
public class FileUploadController {
    @Autowired
    private Environment env;
    @Autowired
    private StudentDao studentDao;

    @RequestMapping("/uploadRedirect")
    public String uploadRedirect() {
        return "add-resume";
    }


    @RequestMapping("/uploadProfilePic")
    public ResponseEntity<?> uploadProfilePic(@RequestParam("uploadfile") MultipartFile uploadfile, HttpSession session) {
        User user= (User) session.getAttribute("activeUser");
        //user = new User("fffff","ggggg");
        if (user != null && (user.getClass() == Student.class) ) {
            if (fileUpload(uploadfile)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String picPath = env.getProperty("paths.uploadedFiles") + "/" + uploadfile.getOriginalFilename();
        studentDao.updatePicPath(user.getId(), picPath);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean fileUpload(@RequestParam("uploadfile") MultipartFile uploadfile) {
        try {
            // Get the filename and build the local file path
            String filename = uploadfile.getOriginalFilename();
            String directory = env.getProperty("paths.uploadedFiles");
            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadfile.getBytes());
            stream.close();
            System.out.println("File successfully uploaded: " + uploadfile.getOriginalFilename());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    @RequestMapping("/uploadResume")
    public ResponseEntity<?> uploadResume(@RequestParam("uploadfile1") MultipartFile uploadfile, HttpSession session) {
        User user= (User) session.getAttribute("activeUser");
        if (user != null && (user.getClass() == Student.class) ) {
            if (fileUpload(uploadfile)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String resumePath = env.getProperty("paths.uploadedFiles") + "/" + uploadfile.getOriginalFilename();
        studentDao.updateResumePath(user.getId(), resumePath);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("uploadfile") MultipartFile uploadfile) {

        if (fileUpload(uploadfile)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
