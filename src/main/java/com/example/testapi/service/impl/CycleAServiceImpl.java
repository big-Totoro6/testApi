package com.example.testapi.service.impl;

import com.example.testapi.service.CycleAService;
import com.example.testapi.service.CycleBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * spring创建对象bean的过程 大概是 先执行构造方法 后完成实例化 在进行属性赋值 完成初始化
 *
 * 但是三级缓存发生在构造器初始化之前 所以如果是构造器注入的话 三级缓存也没用
 *
 * Cache of singleton objects: bean name to bean instance. 一级缓存 只有彻底完成初始化 成为可用对象才可以
 * singletonObjects：用于存放完全初始化好的 bean，从该缓存中取出的 bean 可以直接使用
 *
 * Cache of early singleton objects: bean name to bean instance. 二级缓存
 * earlySingletonObjects：提前曝光的单例对象的cache，存放原始的 bean 对象（尚未填充属性），用于解决循环依赖
 *
 *
 * Cache of singleton factories: bean name to ObjectFactory.三级缓存
 * singletonFactories：单例对象工厂的cache，存放 bean 工厂对象，用于解决循环依赖
 */
@Service
public class CycleAServiceImpl implements CycleAService {
    private CycleBService cycleBService;
    @Autowired
    public void setCycleBService(CycleBService cycleBService) {
        this.cycleBService = cycleBService;
    }

    /**
     * way1：属性注入 使用Autowired
     *     @Autowired
     *     private CycleBService cycleBService;
     *     springboot 2.6.0之前 直接@Autowired是不会造成循环依赖的
     *     2.6.0之后 @Autowired就会造成这种循环依赖 用setter 注入解决
     * way2：
     *     构造器注入 如果有循环依赖 必定报错
     *     如果类只有一个构造方法，那么 @Autowired 注解可以省略；如果类中有多个构造方法，那么需要添加上 @Autowired 来明确指定到底使用哪个构造方法。
     *     private CycleBService cycleBService;
     *     @Autowired
     *     public CycleAServiceImpl(CycleBService cycleBService) {
     *         this.cycleBService = cycleBService;
     *     }
     * way3：setter 注入 属于可选的依赖项
     *     private CycleBService cycleBService;
     *     @Autowired
     *     public void setCycleBService(CycleBService cycleBService) {
     *         this.cycleBService = cycleBService;
     *     }
     */
    @Override
    public void printA() {
        System.out.println("i am A");
        cycleBService.printB();
    }
}