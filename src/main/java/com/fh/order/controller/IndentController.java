package com.fh.order.controller;

import com.fh.order.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("indent")
public class IndentController {
    @Autowired
    private IndentService is;

}
