package com.example.testapi.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class CommonUtilsConfig {
    @Value("${jason.config.name:default}") // 从配置文件读取值
    private String configName;

    @PostConstruct
    public void initStaticField() throws Exception {
        // 通过反射获取 CommonUtils 的私有静态变量 name
        CommonUtils.name = configName;
    }
}
