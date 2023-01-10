package com.example.testapi.dao.impl;

import com.example.testapi.dao.UserDao;
import com.example.testapi.model.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {
    static List<User> userList=new ArrayList<>();
    static {
        Faker fakerWithCN = new Faker(Locale.CHINA);
        for (int i = 0; i < 10; i++) {
            User userInfo = new User();
            userInfo.setPhid(i+1L);
            userInfo.setRealName(fakerWithCN.name().fullName());
            userInfo.setCellPhone(fakerWithCN.phoneNumber().cellPhone());
            userInfo.setCity(fakerWithCN.address().city());
            userInfo.setStreet(fakerWithCN.address().streetAddress());
            userInfo.setUniversityName(fakerWithCN.university().name());
            userList.add(userInfo);
        }
    }
    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void add(User stu) {
    }

    @Override
    public User findById(Long stuId) {
        return userList.stream().filter(t-> t.getPhid().equals(stuId)).findFirst().orElse(null);
    }

    @Override
    public void update(User student) {

    }

    @Override
    public void delete(Long stuId) {

    }
}
