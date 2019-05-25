package com.ssm.controller;

import com.ssm.commons.bean.ResultBean;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/7/20.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginView(){
        return "index";
    }
}
