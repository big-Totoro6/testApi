package com.example.testapi.service.impl;

import org.springframework.stereotype.Service;



@Service
public class QueryGrantTypeService {
    public String getResult(String resourceType) {
        switch (resourceType) {
            case "红包":
                // 查询红包的派发方式
                break;
            case "购物券":
                //  查询购物券的派发方式
                break;
            case "QQ会员":
                break;
            case "外卖会员":
                break;
            default:
                System.out.println("查找不到该优惠券类型resourceType以及对应的派发方式");
                break;
        }
        return "";
    }
}
