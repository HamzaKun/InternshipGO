package com.internshipgo.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza on 11/9/2016.
 */
@Entity
public class Student extends User{
    private String name;
    private String description;
    private String address;
    private String picPath;
    private String resumePath;
    @OneToMany
    private List<Experience> experiences;
    @ManyToMany
    private List<InternshipOffer> internshipOffers;
    @ManyToMany
    private List<Language> languages;
    private String specialization;

    /**
     *To use this we must first set add the student in the internship offer
     * @param internshipOffer
     */
    public void addInternshipOffer(InternshipOffer internshipOffer) {
        if (getInternshipOffers() == null){
            internshipOffers = new ArrayList<InternshipOffer>();
        }
        getInternshipOffers().add(internshipOffer);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Student() {
        super();
    }

    public Student(String mail, String password) {
        super(mail, password);
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<InternshipOffer> getInternshipOffers() {
        return internshipOffers;
    }

    public void setInternshipOffers(List<InternshipOffer> internshipOffers, boolean add) {
        this.internshipOffers =
        this.internshipOffers = internshipOffers;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
