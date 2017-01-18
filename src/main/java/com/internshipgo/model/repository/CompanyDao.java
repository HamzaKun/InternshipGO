package com.internshipgo.model.repository;

import com.internshipgo.model.Company;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hamza on 15/01/17.
 */
public interface CompanyDao extends CrudRepository<Company, String> {
    Company getCompanyById(String company);
}
