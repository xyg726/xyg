package com.fh.inteceptor;

import com.alibaba.fastjson.JSONObject;
import com.fh.common.Ignore;
import com.fh.member.model.User;
import com.fh.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class LoginInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理客户端传过来的自定义头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"x-auth,mtoken,content-type");
        //处理客户端发过来put,delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"PUT,POST,DELETE,GET");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Ignore.class)){
            return true;
        }
        //获取请求头里面的token
        String token = request.getHeader("x-auth");
        if(StringUtils.isEmpty(token)){
            throw  new LoginException();
        }
        //验证token
        boolean res = JwtUtil.verify(token);
        if(res){
            String userString = JwtUtil.getUser(token);
            String jsonUser = URLDecoder.decode(userString,"utf-8");
            User user = JSONObject.parseObject(jsonUser,User.class);
            System.out.println(user.toString());
        }else {
            throw new LoginException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
