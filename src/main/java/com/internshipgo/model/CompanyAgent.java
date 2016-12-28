package com.internshipgo.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by hamza on 28/11/16.
 */
@Entity
public class CompanyAgent extends User {

    public CompanyAgent() {
    }

    public CompanyAgent(Company company) {
        this.company = company;
    }

    public CompanyAgent(String mail, String password, Company company) {
        super(mail, password);
        this.company = company;
    }

    @ManyToOne
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
