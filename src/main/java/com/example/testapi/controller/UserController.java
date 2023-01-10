package com.example.testapi.controller;

import cn.hutool.core.convert.Convert;
import com.example.testapi.common.SaveData;
import com.example.testapi.common.SaveParam;
import com.example.testapi.dao.UserDao;
import com.example.testapi.model.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Controller
@RequestMapping("/User/")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("getUserList")
    @ResponseBody
    public  Object getUserList(){
        List<User> users = userDao.findAll();
        HashMap<String, Object> result = new HashMap<>();
        result.put("totalRows",users.size());
        result.put("Record",users);
        return result;
    }

    @PostMapping("GetUser")
    @ResponseBody
    public Object getUser(Long phid){
        return userDao.findById(phid);
    }

    @PostMapping("TestPost")
    @ResponseBody
    public Object testPost(@RequestBody User  user){
        return "testPost";
    }

    @PostMapping("TestRequest")
    @ResponseBody
    public Object testRequest(User  user, @RequestParam Map<  String,Object> map){
        Optional.ofNullable(map.get("proj")).ifPresent(t->{
            List<Long> longs = Convert.toList(Long.class, t);
            if (longs.size()>0){
                System.out.println("成功");
            }

        });
        return "TestRequest";
    }

    @PostMapping("TestSave")
    @ResponseBody
    public Object testSave(@SaveParam SaveData data){
        HashMap<String, String> mst = data.getMst();
        HashMap<String, String> detail = data.getDetail();
        return "TestSave";
    }

}
