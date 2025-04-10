package com.example.testapi.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class CommonUtils {

    public static String name;

    public static String propertyName;
    public static void iWillGetConfigFromConfig(){
        log.info("jason.config.name:{}",name);
        log.info("jasonProperty.config.name:{}",propertyName);
    }
}
