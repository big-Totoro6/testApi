package com.example.testapi.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class SaveData {
    private HashMap<String,String> mst;
    private HashMap<String,String> detail;

    /**
     * 新建一个类的时候 给私有变量初始化
     * 因为用的是HashMap，不是基本对象，不初始化分配内存空间取到的是空对象
     */
    public SaveData(){
        mst=new HashMap<>();
        detail=new HashMap<>();
    }
}
