package com.internshipgo.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.List;

/**
 * Created by hamza on 12/01/17.
 */
@Controller
public class ConventionController {
    @Autowired
    private Environment env;

    @RequestMapping("/testing")
    String GenerateConvention() {
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
//TODO: Getting and setting the right values to generate the pdf
                System.out.println("f is: " + f);
                if (f.contains("Company name")) {
                    System.out.println("in here");
                    String value = "company name";
                    field.setValue(value);
                    System.out.println("printed: " + value + " to: " + f);
                    field.setReadOnly(true);
                } else if( f.contains("Student name")) {
                    System.out.println(" The student name");
                    String name = "student name";
                    field.setValue(name);
                    field.setReadOnly(true);
                } else if( f.contains("adress")) {
                    String adress = "Testing the adress";
                    field.setValue(adress);
                    field.setReadOnly(true);
                } else if (f.contains("Salary")) {
                    String salary = "1900";
                    field.setValue(salary);
                    field.setReadOnly(true);
                } else if (f.contains("Start date")) {
                    String s_date = "12/12/1212";
                    field.setValue(s_date);
                    field.setReadOnly(true);
                } else if (f.contains("Finish date")) {
                    String f_date = "01/12/1213";
                    field.setValue(f_date);
                    field.setReadOnly(true);
                }
            }
            String homePath = env.getProperty("paths.uploadedFiles");
            // Save edited file
            pdfDoc.save(homePath + "/new file.pdf");
            pdfDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "index";
        }
        /*PDDocumentCatalog docCatalog = pdfDoc.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        PDField field = acroForm.getField("applicationPrepaid[0].#pageSet[0].Pagina1[0].txtFirstName[0]");
        */

        return "index";
    }
}
