package com.internshipgo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by user on 09/01/2017.
 */
@Entity
public class OfferStatus {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private InternshipOffer internshipOffer;
    @ManyToOne
    private Student student;
    private int companyResponse;
    private int studentResponse;

    public int getCompanyResponse() {
        return companyResponse;
    }

    public void setCompanyResponse(int companyResponse) {
        this.companyResponse = companyResponse;
    }

    public int getStudentResponse() {
        return studentResponse;
    }

    public void setStudentResponse(int studentResponse) {
        this.studentResponse = studentResponse;
    }

    public OfferStatus() {
        super();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "OfferStatus{" +
                "id=" + id +
                ", internshipOffer=" + internshipOffer +
                ", student=" + student +
                '}';
    }

    public OfferStatus(InternshipOffer internshipOffer, Student student) {
        this.internshipOffer = internshipOffer;
        this.student = student;
    }

    public InternshipOffer getInternshipOffer() {

        return internshipOffer;
    }

    public void setInternshipOffer(InternshipOffer internshipOffer) {
        this.internshipOffer = internshipOffer;
    }
}
