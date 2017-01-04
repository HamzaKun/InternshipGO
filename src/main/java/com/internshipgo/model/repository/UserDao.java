package com.internshipgo.model.repository;

import com.internshipgo.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hamza on 22/11/16.
 */
//@Repository
public interface UserDao extends CrudRepository<User, Long> {
    List<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    @Modifying
    @Transactional
    @Query("update User u set u.userName = ?2 where u.id = ?1")
    void updateUserName(Long id, String userName);
    @Modifying
    @Transactional
    @Query("update User u set u.email = ?2 where u.id = ?1")
    void updateEmail(Long id, String email);

}
