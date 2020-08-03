package com.fh.member.controller;


import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.member.model.User;
import com.fh.member.service.UserService;

import com.fh.util.RedisUtil;
import com.fh.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("checkMemberName")
    @Ignore
    public ServerResponse checkMemberName(String name){
        return userService.checkMemberName(name);
    }

    @RequestMapping("checkMemberPhone")
    @Ignore
    public ServerResponse checkMemberPhone(String phone){
        return userService.checkMemberPhone(phone);
    }


    @RequestMapping("register")
    @Ignore
    public ServerResponse register(User user){
        return userService.register(user);
    }

    //验证码
    @RequestMapping("redister")
    @Ignore
    public ServerResponse  redister(User user){
        return  userService.redister(user);
    }


   @RequestMapping("login")
    @Ignore
    public ServerResponse login(User user,HttpServletRequest request){
        return userService.login(user,request);
    }


    @RequestMapping("out")
    @Ignore
    public ServerResponse out(HttpServletRequest request){
        //让token失效
        String token = (String) request.getSession().getAttribute(SystemConstant.TOKEN_ERROR);
        RedisUtil.del(SystemConstant.TOKEN_ERROR + token);
        request.getSession().removeAttribute(SystemConstant.TOKEN_ERROR);
        //清除session中用户信息
        request.getSession().removeAttribute(SystemConstant.SESSION_KEY);
        return ServerResponse.success();
    }

}
