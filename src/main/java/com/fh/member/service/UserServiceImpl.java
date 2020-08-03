package com.fh.user.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ServerResponse;
import com.fh.member.model.User;
import com.fh.member.mapper.UserMapper;
import com.fh.member.service.UserService;
import com.fh.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ServerResponse checkMemberName(String name) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        User user =userMapper.selectOne(queryWrapper);
        if(user==null){
          return ServerResponse.success();
        }
        return ServerResponse.error("用户已存在");
    }

    @Override
    public ServerResponse checkMemberPhone(String phone) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            return ServerResponse.success();
        }
        return ServerResponse.error("手机号已存在");
    }


    @Override
    public ServerResponse register(User user) {
        String redisCode = RedisUtil.get(user.getPhone());
        if(redisCode==null){
             return ServerResponse.error("验证码失效");
        }
        if(!redisCode.equals(user.getCode())){
            return ServerResponse.error("验证码错误");
        }
        userMapper.insert(user);
        return ServerResponse.success();
    }

    @Override
    //验证码
    public ServerResponse redister(User user) {
        String redisCode = RedisUtil.get(user.getCode());
        if (redisCode==null){
            return  ServerResponse.error("验证码失效");
        }
        if (!redisCode.equals(user.getCode())){
            return  ServerResponse.error("验证码不正确");
        }
        userMapper.insert(user);
        return ServerResponse.success();
    }

    @Override
    //登录
    public ServerResponse login(User user,HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //比较用户名是否正确
        queryWrapper.eq("name",user.getName());
        //或者
        queryWrapper.or();
        //比较手机号是否正确
        queryWrapper.eq("phone",user.getPhone());
        User userDB = userMapper.selectOne(queryWrapper);
        if (userDB==null){
            return ServerResponse.error("用户名或者手机号不存在");
        }
        //判断密码
        if (!user.getPassword().equals(userDB.getPassword())){
            return ServerResponse.error("密码不正确");
        }
        //把用户放入到session对象中去
        request.getSession().setAttribute(SystemConstant.SESSION_KEY,userDB);
        //账号密码正确   生成tocken 返回前台
        String token =null;
        try {
            //转换jsonstring形式
            String jsonString = JSONObject.toJSONString(userDB);
            //编译格式
            String encodeJson = URLEncoder.encode(jsonString, "utf-8");
            //生成token
            token = JwtUtil.sign(encodeJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //返回给前台
        return ServerResponse.success(token);
    }



}
