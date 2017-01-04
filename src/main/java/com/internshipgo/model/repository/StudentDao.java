package com.internshipgo.model.repository;

import com.internshipgo.model.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hamza on 28/12/16.
 */
public interface StudentDao extends CrudRepository<Student, Long> {
    @Modifying
    @Transactional
    @Query("update Student u set u.picPath = ?2 where u.id = ?1")
    void updatePicPath(Long id, String pic);
    @Modifying
    @Transactional
    @Query("update Student u set u.resumePath = ?2 where u.id = ?1")
    void updateResumePath(Long id, String resume);
}
