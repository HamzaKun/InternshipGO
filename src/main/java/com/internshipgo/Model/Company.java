package com.internshipgo.Model;

/**
 * Created by Hamza on 11/9/2016.
 */
public class Company extends User{
    private Type type;
    private CompanySize companySize;
    private Industry industry;
    private String headquarters;
    private long founded;
    private String webSite;

    public Company(String mail, String password) {
        super(mail, password);
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
