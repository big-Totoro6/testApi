package com.example.testapi.service.impl;

import com.example.testapi.service.CycleAService;
import com.example.testapi.service.CycleBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CycleBServiceImpl implements CycleBService {
    /**
     * 使用构造器注入时 如果A注入B B注入A 时 就会发生循环依赖问题
     * ┌─────┐
     * |  cycleAServiceImpl defined in file
     * ↑     ↓
     * |  cycleBServiceImpl defined in file
     * └─────┘
     */
    private CycleAService cycleAService;
    @Autowired
    public CycleBServiceImpl(CycleAService cycleAService) {
        this.cycleAService = cycleAService;
    }

    @Override
    public void printB() {
        System.out.println("i am B");
        cycleAService.printA();
    }
}
