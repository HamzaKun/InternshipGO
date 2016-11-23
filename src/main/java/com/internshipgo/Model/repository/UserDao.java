package com.internshipgo.Model.repository;

import com.internshipgo.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hamza on 22/11/16.
 */
//@Repository
public interface UserDao extends CrudRepository<User, Long> {
    List<User> findByEmail(String email);
}
