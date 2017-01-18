package com.internshipgo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by hamza on 28/11/16.
 */
@Entity
public class CompanyAgent extends User {
    //TODO: adding the FSD and accept/refuse, get the offer's description; Messagerie externe
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Company company;

    private String companyname;

    public CompanyAgent() {
    }

    public CompanyAgent(Company company) {
        this.company = company;
    }

    public CompanyAgent(String mail, String password, Company company) {
        super(mail, password);
        this.company = company;
    }



    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
