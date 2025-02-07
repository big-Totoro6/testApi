package com.example.testapi.controller;

import com.example.testapi.common.BizException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/play")
public class JustForPlayController {
    @GetMapping
    public ResponseEntity testExceptionHandler(){
        throw new BizException("业务传参有问题");
    }
}
