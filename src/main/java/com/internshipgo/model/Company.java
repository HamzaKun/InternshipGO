package com.internshipgo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Hamza on 11/9/2016.
 */
@Entity
public class Company {
    @Id
    @GeneratedValue
    private String id;
    private Type type;
    private CompanySize companySize;
    private Industry industry;
    private String headquarters;
    private long founded;
    private String webSite;
    private String address;
    @OneToMany
    private List<CompanyAgent> agents;

    public List<CompanyAgent> getAgents() {
        return agents;
    }

    public void setAgents(List<CompanyAgent> agents) {
        this.agents = agents;
    }

    /*public Company(String mail, String password) {
        super(mail, password);
    }*/

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public CompanySize getCompanySize() {
        return companySize;
    }

    public void setCompanySize(CompanySize companySize) {
        this.companySize = companySize;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public long getFounded() {
        return founded;
    }

    public void setFounded(long founded) {
        this.founded = founded;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
