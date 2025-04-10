package com.example.testapi.common.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jason-property.config")
public class JasonConfig {
    private String name;
    // 省略 getter/setter
    public void setName(String name) {
        this.name = name;
        CommonUtils.propertyName = name; // 直接更新静态变量
    }
}