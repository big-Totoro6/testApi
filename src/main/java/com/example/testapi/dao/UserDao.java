package com.example.testapi.dao;

import com.example.testapi.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    void add(User stu);

    User findById(Long stuId);

    void update(User student);

    void delete(Long stuId);

}
