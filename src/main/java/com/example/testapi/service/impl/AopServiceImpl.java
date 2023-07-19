package com.example.testapi.service.impl;

import com.example.testapi.common.DiyLog;
import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl {

    @DiyLog
    public String printAop(){
        return "Its printAop wooooooo";
    }
}
