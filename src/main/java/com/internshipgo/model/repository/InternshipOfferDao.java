package com.internshipgo.model.repository;

import com.internshipgo.model.InternshipOffer;
import com.internshipgo.model.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by awang on 2017/1/5.
 */
public interface InternshipOfferDao extends CrudRepository<InternshipOffer,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE InternshipOffer offer set offer.students = ?1 where offer.id = ?2")
    void apply(Student student,Long offerId);

    @Query("select offer from InternshipOffer offer")
    public List<InternshipOffer> getALL();

    @Query("select o from InternshipOffer o where o.companyName = ?1")
    public List<InternshipOffer> getCompanyOffer(String companyName);
}
