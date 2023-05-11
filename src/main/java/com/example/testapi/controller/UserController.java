package com.example.testapi.controller;

import cn.hutool.core.convert.Convert;
import com.example.testapi.common.SaveData;
import com.example.testapi.common.SaveParam;
import com.example.testapi.dao.UserDao;
import com.example.testapi.model.User;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Object testRequest(User  user, @RequestParam Map<String,Object> map){
        Optional.ofNullable(map.get("proj")).ifPresent(t->{
            List<Long> longs = Convert.toList(Long.class, t);
            if (longs.size()>0){
                System.out.println("成功");
            }

        });
        return "TestRequest";
    }

    @PostMapping("TestLocalDataTime")
    @ResponseBody
    public Object testLocalDataTime(User  user){
        System.out.println(user.getExpireTime());
        return "testLocalDataTime";
    }

    /**
     * 测试JsonAnyGetter
     * @param user
     * @return
     */
    @PostMapping("TestJsonRow")
    @ResponseBody
    public Object testJsonRow(@RequestBody User  user){
        System.out.println(user.toString());
        return "TestJsonRow";
    }

    @PostMapping("TestSave")
    @ResponseBody
    public Object testSave(@SaveParam SaveData data){
        HashMap<String, String> mst = data.getMst();
        HashMap<String, String> detail = data.getDetail();
        return "TestSave";
    }

    @PostMapping("TestList")
    @ResponseBody
    public Object testList(@RequestParam(value = "taskPhids") String taskPhids) {
        HashMap<String, String> result = new HashMap<>();
        String[] arr = StringUtils.substringsBetween(taskPhids, "[", "]");
        List<String> s = Arrays.asList(arr);
        s.forEach(t -> {
            List<String> list = Arrays.asList(t.split(","));
            result.put(list.get(0), "value");
        });
        return result;
    }

    @PostMapping("TestRequestBodyList")
    @ResponseBody
    public Object testRequestBodyList(@RequestBody List<Long> taskPhids) {
        taskPhids.forEach(System.out::println);
        return taskPhids;
    }

    @PostMapping("TestRequestBodyPhidList")
    @ResponseBody
    public Object testRequestBodyPhidList(@RequestBody Long taskPhids) {
        return taskPhids;
    }
}
