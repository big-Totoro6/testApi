package com.example.testapi.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
//@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
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

    /**
     * 改变名称
     */
    @JsonProperty("cName")
    private String cName;

    private Map<String, Object> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void setProperties(String name, Object value) {
        properties.put(name, value);
    }

    /**
     * 当在一个类的成员变量上使用 @DateTimeFormat 注解时，表示该成员变量可以接收指定格式的日期时间字符串，并将其转换成 LocalDateTime 类型
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime expireTime;
}