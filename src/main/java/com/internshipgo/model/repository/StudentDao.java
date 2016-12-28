package com.internshipgo.model.repository;

import com.internshipgo.model.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hamza on 28/12/16.
 */
public interface StudentDao extends CrudRepository<Student, Long> {
}
