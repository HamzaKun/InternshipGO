package com.internshipgo.model.repository;

import com.internshipgo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hamza on 22/11/16.
 */
//@Repository
public interface UserDao extends CrudRepository<User, Long> {
    List<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
