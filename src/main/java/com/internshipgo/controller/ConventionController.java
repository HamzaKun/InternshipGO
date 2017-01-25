package com.internshipgo.controller;

import com.internshipgo.model.OfferStatus;
import com.internshipgo.model.Student;
import com.internshipgo.model.User;
import com.internshipgo.model.repository.OfferStatusDao;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by hamza on 12/01/17.
 */
@Controller
public class ConventionController {
    @Autowired
    private Environment env;
    @Autowired
    private OfferStatusDao offerStatusDao;

    @RequestMapping("/downloadConvention/{offerId}")
    String GenerateConvention(HttpServletResponse response, HttpSession session, @PathVariable Long offerId) {
        User user = (User) session.getAttribute("activeUser");
        if ( user != null && user.getClass() == Student.class) {
            OfferStatus offer = offerStatusDao.getOfferStatusById(offerId);
            String fileName = "General_Convention.pdf";
            String filePath = "./resources/";
            try {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                InputStream is = classloader.getResourceAsStream(filePath + fileName);
                PDDocument pdfDoc = null;
                pdfDoc = PDDocument.load(is);
                PDDocumentCatalog docCatalog = pdfDoc.getDocumentCatalog();
                PDAcroForm acroForm = docCatalog.getAcroForm();
                // Get field names
                List<PDField> fieldList = acroForm.getFields();
                // String the object array
                String[] fieldArray = new String[fieldList.size()];
                int i = 0;
                for (PDField sField : fieldList) {
                    fieldArray[i] = sField.getFullyQualifiedName();
                    System.out.println(fieldArray[i]);
                    i++;
                }
                // Loop through each field in the array and do something
                for (String f : fieldArray) {
                    PDField field = acroForm.getField(f);
                    System.out.println("f is: " + f);
                    if (f.contains("Company name")) {
                        System.out.println("in here");
                        field.setValue(offer.getInternshipOffer().getCompanyName());
                        System.out.println("printed: " + offer.getInternshipOffer().getCompanyName() + " to: " + f);
                        field.setReadOnly(true);
                    } else if( f.contains("Student name")) {
                        System.out.println(" The student name");
                        field.setValue(offer.getStudent().getName());
                        field.setReadOnly(true);
                    } else if( f.contains("adress")) {
                        String adress = "Testing the adress";
                        field.setValue(offer.getStudent().getAddress());
                        field.setReadOnly(true);
                    } else if (f.contains("Salary")) {
                        String salary = "1000";
                        field.setValue(salary);
                        field.setReadOnly(true);
                    } else if (f.contains("Start date")) {
                        String s_date = "01/02/2016";
                        field.setValue(s_date);
                        field.setReadOnly(true);
                    } else if (f.contains("Finish date")) {
                        String f_date = "01/08/2017";
                        field.setValue(f_date);
                        field.setReadOnly(true);
                    }
                }
                String homePath = env.getProperty("paths.uploadedFiles");
                String conventionPath = homePath + "/" + offer.getId() + "_" + offer.getStudent().getUserName() + ".pdf";
                pdfDoc.save(homePath + "/" + offer.getId() + "_" + offer.getStudent().getUserName() + ".pdf");
                pdfDoc.close();
                File file = new File(conventionPath);
//                File file = new File(homePath + File.separator + fileName);
                String mimeType= URLConnection.guessContentTypeFromName(file.getName());
                if(mimeType==null){
                    System.out.println("mimetype is not detectable, will take default");
                    mimeType = "application/octet-stream";
                }
                System.out.println("mimetype : "+mimeType);
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + conventionPath +"\""));
                response.setContentLength((int)file.length());
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return "index";
            }
        } else {
            return "redirect:/index";
        }
        return "index";
    }
}
