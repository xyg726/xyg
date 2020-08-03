package com.fh.order.service;

import com.fh.order.mapper.IndentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndentServiceImpl implements IndentService {
    @Autowired
    private IndentMapper im;

}
