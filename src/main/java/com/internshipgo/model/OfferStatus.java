package com.internshipgo.model;

import javax.persistence.*;

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
    @Column(columnDefinition = "default '-1'")
    private int companyResponse;
    @Column(columnDefinition = "default '-1'")
    private int studentResponse;
    private String motivation;

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

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
