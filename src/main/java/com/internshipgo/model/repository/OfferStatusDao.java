package com.internshipgo.model.repository;

import com.internshipgo.model.Company;
import com.internshipgo.model.OfferStatus;
import com.internshipgo.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hamza on 18/01/17.
 */
public interface OfferStatusDao extends CrudRepository<OfferStatus, Long> {
    List<OfferStatus> getOfferStatusByStudent(Student student);
    @Query("SELECT  o from OfferStatus o where o.internshipOffer.company.id = ?1")
    List<OfferStatus> getApplyingStudents(String company);
    @Query("SELECT  o from OfferStatus o where o.internshipOffer.companyName = ?1 and o.companyResponse < 2")
    List<OfferStatus> getApplyingUnrespondedStudents(String company);
    OfferStatus getOfferStatusById(Long id);
    @Query("select o from OfferStatus o where o.id = ?1 and o.student.id = ?2")
    OfferStatus getOfferStatusByIdAndInternshipId(Long id, Long intId);
    @Query("select o from OfferStatus o where o.student.id = ?1 and o.companyResponse = 2 and o.studentResponse = 1")
    List<OfferStatus> getAcceptedOfferStatusByStudentId(Long studentId);
}
