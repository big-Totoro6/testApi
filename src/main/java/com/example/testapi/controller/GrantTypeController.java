package com.example.testapi.controller;

import com.example.testapi.service.impl.QueryGrantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 根据优惠券的类型resourceType和编码resourceId来 查询 发放方式grantType和领取规则
 */
@RestController
public class GrantTypeController {

    @Autowired
    private QueryGrantTypeService queryGrantTypeService;

    @PostMapping("/grantType")
    public String test(String resourceType){
        return queryGrantTypeService.getResult(resourceType);
    }
}