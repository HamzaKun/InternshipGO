package com.internshipgo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Hamza on 1/3/2017.
 */
@Entity
public class InternshipOffer {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private int duration;
    private int companyResponse;
    private int yearHeadResponse;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Student> students;
    @ManyToOne
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCompanyResponse() {
        return companyResponse;
    }

    public void setCompanyResponse(int companyResponse) {
        this.companyResponse = companyResponse;
    }

    public int getYearHeadResponse() {
        return yearHeadResponse;
    }

    public void setYearHeadResponse(int yearHeadResponse) {
        this.yearHeadResponse = yearHeadResponse;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company companies) {
        this.company = companies;
    }
}