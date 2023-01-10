package com.example.testapi.model;

import lombok.Data;

@Data
public class User {
    private Long phid;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机
     */
    private String cellPhone;
    /**
     * 大学
     */
    private String universityName;
    /**
     * 城市
     */
    private String city;
    /**
     * 地址
     */
    private String street;
    /**
     * 吃的食物
     */
    private Food food;
}