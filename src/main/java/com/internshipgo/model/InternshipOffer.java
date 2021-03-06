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
    private String title;
    private String description;
    private int duration;
    private int companyResponse;
    //TODO : Acceptation de l'étudiant
    private int studentResponse;
    private int yearHeadResponse;
    private String city;
    private String companyName;
    @ManyToOne
    private Company company;

    public InternshipOffer() {
        super();

    }

    public InternshipOffer(String title, String description, int duration,
                           int companyResponse, int yearHeadResponse, String city,
                           String companyName) {
        super();
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.companyResponse = companyResponse;
        this.yearHeadResponse = yearHeadResponse;
        this.city = city;
        this.companyName = companyName;
    }

    public InternshipOffer(Long id,String title, String description, int duration,
                           int companyResponse, int yearHeadResponse, String city,
                           String companyName) {
        super();
        this.id=id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.companyResponse = companyResponse;
        this.yearHeadResponse = yearHeadResponse;
        this.city = city;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCompanyName() {     return companyName;     }

    public void setCompanyName(String companyName) { this.companyName = companyName;    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company companies) {
        this.company = companies;
    }

    @Override
    public String toString() {
        return "InternshipOffer [title=" + title + ", description="
                + "ok" + ", duration=" + duration + ", companyResponse="
                + companyResponse + ", yearHeadResponse=" + yearHeadResponse
                + ", city=" + city + ", companyName=" + companyName + "]";
    }

    public void describe(){
        System.out.println(this);
    }
}
