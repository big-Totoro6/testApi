package com.example.testapi.controller;

import cn.hutool.core.convert.Convert;
import com.example.testapi.common.DiyLog;
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
    /**
     * 注解注入 最常見
     * 知识点
     * 使用 @Autowired 注解标记的 setUserDAO() 方法将会按照 byType 方式注入 UserDAO 类型的 bean。
     *
     * 然而，如果有多个符合条件的 bean（即多个 UserDAO 类型的 bean），Spring 将会抛出异常，
     * 因为无法确定要注入哪个 bean。这时候，你需要使用 @Qualifier 注解来指定具体要注入的 bean，或者使用 byName 方式。
     * @return
     */
    @Autowired
    private UserDao userDao;

    /**
     * 构造器注入
     */
//    private UserDao userDao;
//    public UserController(UserDao userDao){
//        this.userDao=userDao;
//    }

    /**
     * setter注入
     * 必须加@Autowired
     * @return
     */
//    private UserDao userDao;
//    @Autowired
//    public void setUserDao(UserDao userDao){
//        this.userDao=userDao;
//    }

    @GetMapping("getUserList")
    @ResponseBody
    @DiyLog
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
