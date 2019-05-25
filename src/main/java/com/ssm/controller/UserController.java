package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.ssm.commons.bean.ResultBean;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/7/20.
 */
@RestController
public class UserController {


    @Autowired(required = false)
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getUserById(){
//        User user = userService.getUserById(1);
//        String userStr= JSON.toJSONString(user);
        return "userStr";
    }

    /**
     * 微信创建用户信息
     * @return
     */
    @RequestMapping(value = "/user/adduser",method = RequestMethod.GET)
    public ResultBean adduser(@RequestBody User user){
//        User user = userService.getUserById(1);
//        String userStr= JSON.toJSONString(user);
        return userService.insertUser(user);
    }

    /**
     * 查找openid 用户信息
     * @return
     */
    @RequestMapping(value = "/user/openid",method = RequestMethod.GET)
    public ResultBean getUserByOpenid(@RequestParam String openid){
        return userService.getUserByOpenid(openid);
    }
}
