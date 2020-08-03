package com.fh.member.service;

import com.fh.common.ServerResponse;
import com.fh.member.model.User;

import javax.servlet.http.HttpServletRequest;


public interface UserService {

    ServerResponse checkMemberName(String name);

    ServerResponse checkMemberPhone(String phone);

    ServerResponse register(User user);

    ServerResponse login(User user, HttpServletRequest request);

    ServerResponse redister(User user);

}
